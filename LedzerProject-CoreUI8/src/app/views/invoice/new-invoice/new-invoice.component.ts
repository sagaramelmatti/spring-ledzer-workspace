import {AfterViewInit, Component, ViewChild,ElementRef, TemplateRef, OnInit, Inject,NgModule } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { VariableAst } from '@angular/compiler';
import { error } from '../../../../../node_modules/protractor';
import { MatSort, MatTableDataSource, MatPaginator, MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatIconModule } from '@angular/material';
import { DialogOverviewExampleDialogComponent } from './../../dialog-overview-example-dialog/dialog-overview-example-dialog.component';
import { Alert } from '../../../../../node_modules/@types/selenium-webdriver';
import { FormControl, Validators,FormsModule } from '@angular/forms';
import {debounceTime, distinctUntilChanged, startWith, tap, delay} from 'rxjs/operators';
import {merge} from "rxjs/observable/merge";
import {fromEvent} from 'rxjs/observable/fromEvent';
import  { Subscription } from 'rxjs';
import  { InvoiceService}  from '../../../shared_service/invoice.service';
import 	{Invoice}  from '../invoice';
import 	{InvoiceDetail}  from '../invoiceDetail';
import 	{Product}  from '../../product/product';
import 	{Uom}  from '../../uom/uom';
import 	{Customer}  from '../../customer/customer';
import 	{Business}  from '../../business/business';
import 	{Tax}  from '../../tax/tax';
import  { Category } from '../../category/category';
import 	{State}  from '../../customer/state';
import { PaymentMode } from '../paymentMode';
import  { UserAccount } from '../../accounts/user-account';
import  { CommonService  }  from '../../../shared_service/common.service';
import  { CustomerService  }  from '../../../shared_service/customer.service';
import  { BusinessService  }  from '../../../shared_service/business.service';
import  { TaxService  }  from '../../../shared_service/tax.service';
import  { CategoryService  }  from '../../../shared_service/category.service';
import  { UomService  }  from '../../../shared_service/uom.service';
import  { AccountsService  }  from '../../../shared_service/accounts.service';
import  { ProductService  }  from '../../../shared_service/product.service';
import {    InvoiceDetailDataSource} from "../invoiceDetail.dataSource";
import {    catchError, finalize} from "rxjs/operators";


@Component({
    selector: 'app-new-invoice',
    templateUrl: './new-invoice.component.html',
    styleUrls: ['./new-invoice.component.css'],
  })
  export class NewInvoiceComponent implements OnInit {

    sub: Subscription;
    inv: Invoice = new Invoice();
    uom: Uom = new Uom();
    tax: Tax = new Tax();
    invoiceDetail: InvoiceDetail;


    public tax_list:Tax[];
    public category_list:Category[];
    public uom_list:Uom[];
    public customer_list:Customer[];
    public payment_mode_list:PaymentMode[];
    public account_list:UserAccount[];
    public state_list:State[];
    public product_list:Product[];
    public invoice_list:Invoice[];

    /* Object Declaration */

    invoiceDetailsList: InvoiceDetail[] = [];
	
    businessId : number;
    customer_state : number;
    business_state : number;

    invoice: Invoice = new Invoice();

    /* invoice details object data */
    pre_taxableAmt  : number;
    pre_sgstAmt     : number;
    pre_cgstAmt     : number;
    pre_igstAmt     : number;
    
    /*  invoice details object data End */

    @ViewChild('paginator', { static: false, }) paginator: MatPaginator;
    @ViewChild('sort', { static: false, }) sort: MatSort;
    @ViewChild('input',{ static: false, }) input: ElementRef;

    addNewUser: InvoiceDetail[] = [
        { id: 0,productId: null ,preProductId: null , hsnCode: null, rate: null, uomId: null,quantity : 0,preQuantity : 0,totalPrice : 0.00,discPer : 0.00,discAmt : 0.00,taxableAmt : 0.00,sgstPer : 0.00,sgstAmt : 0.00,cgstPer : 0.00,cgstAmt : 0.00,igstPer : 0.00,igstAmt : 0.00,invoiceId: null }
    ];

    invoice2: Invoice = new Invoice();
    invoice_details_list: Array<InvoiceDetail>;
    showTable: boolean;
    statusMessage: string;
    isLoaded: boolean = true;
    displayedColumnsUsers: string[] = ['id', 'productId','preProductId','rate', 'hsnCode',  'uomId','quantity','preQuantity', 'totalPrice', 'discPer','discAmt', 'taxableAmt', 'sgstPer','sgstAmt','cgstPer','cgstAmt','igstPer','igstAmt','invoiceId', 'Change', 'Delete'];
    displayedColumnsAddUser: string[] = ['productId','preProductId','hsnCode',  'rate',  'uomId','quantity','preQuantity',  'totalPrice', 'discPer','discAmt', 'taxableAmt', 'sgstPer','sgstAmt','cgstPer','cgstAmt','igstPer','igstAmt','invoiceId', 'Save', 'Cancel'];
    dataSourceUsers: any;
    dataSourceAddUser: any;
    dataSourceInvoiceDetailsList: any;
    

    constructor(private route: ActivatedRoute,private invoiceService: InvoiceService, private commonService:CommonService,private customerService:CustomerService,private taxService:TaxService,private categoryService:CategoryService,
        private uomService:UomService,private accountsService:AccountsService,private productService:ProductService,private businessService:BusinessService, 
         public dialog: MatDialog, public snackBar: MatSnackBar) 
    {
        this.invoice_details_list = new Array<InvoiceDetail>();
    }

    ngOnInit() {

        this.taxService.getAll().subscribe((tax_list)=>{
            this.tax_list=tax_list;
            },(error)=>{
              console.log(error);
            }),
      
            this.categoryService.getAll().subscribe((category_list)=>{
              this.category_list=category_list;
              },(error)=>{
                console.log(error);
            }),
            this.uomService.getAll().subscribe((uom_list)=>{
              this.uom_list=uom_list;
              },(error)=>{
                console.log(error);
            }),
            this.customerService.getAll().subscribe((customer_list)=>{
                this.customer_list=customer_list;
                },(error)=>{
                  console.log(error);
              }),
              this.commonService.getAllPaymentModes().subscribe((payment_mode_list)=>{
                this.payment_mode_list=payment_mode_list;
                },(error)=>{
                  console.log(error);
              }),
              this.accountsService.getAll().subscribe((account_list)=>{
                this.account_list=account_list;
                },(error)=>{
                  console.log(error);
              }),
        
              this.commonService.getAll().subscribe((state_list)=>{
                this.state_list=state_list;
                },(error)=>{
                  console.log(error);
              }),
              this.productService.getAll().subscribe((product_list)=>{
                this.product_list=product_list;
                },(error)=>{
                  console.log(error);
              }),
              this.invoiceService.getAll().subscribe((invoice_list)=>{
                this.invoice_list=invoice_list;
                },(error)=>{
                  console.log(error);
              }),   

            this.isLoaded = true;

            this.sub = this.route.params.subscribe(params => {
                const id = params['id'];
                if (id) 
                {
                    this.invoiceService.get(id).subscribe((invoice_obj: Invoice) => {
                    if (invoice_obj) 
                    {
                      this.inv = invoice_obj;
                      this.inv.invoiceDate = invoice_obj.invoiceDate;
                      this.loadInvoices(this.inv.id);
                      
                    } else {
                      console.log(`Invoice with id '${invoice_obj.id}' not found, returning to list`);
                      //this.gotoList();
                    }
                  });
                }
                else
                {
                    console.log("this inv id is null");
                    this.accountsService.getDefaultAccount().subscribe((default_acc_id : number)=>{
                        this.inv.accountId = default_acc_id;
                        },
                        (error)=>{
                            console.log(error);
                    });

                    this.invoiceService.getNextInvoiceId().subscribe((inv_id : number)=>{
                    if(inv_id == null || inv_id == undefined)
                    {
                        inv_id =1;
                    }
                    this.inv.id = inv_id;
                    this.inv.name = "INV000"+inv_id;
                    this.inv.invoiceNo = inv_id;
                    /* creating Empty Objects */
                    this.tax.id = null;
                    this.inv.invoiceDate = null;
                    this.inv.customerId = null;
                    this.inv.amountPaid = 0.00;
                    this.inv.amountPaidHid = 0.00;
                    this.inv.amountDue = 0.00;
                    this.inv.amountDueHid = 0.00;
                    this.inv.taxAmt = 0.00;
                    this.inv.taxAmtHid = 0.00;
                    this.inv.discountAmt = 0.00;
                    this.inv.billAmt = 0.00;
                    this.inv.billAmtHid = 0.00;
                    //this.invoiceId.addDiscAmt = 0.00;
                    this.inv.taxAmt = 0.00;
                    this.inv.roundAmt = 0.00;
                    this.inv.invoiceAmt = 0.00;
                    this.inv.invoiceAmtHid = 0.00;
                    this.inv.accountId = null;
                    this.inv.paymentModeId = 1;
                    this.inv.chequeNo = null;
                    this.inv.bankName = null;
                    },
                    (error)=>{
                        console.log(error);
                    })
                    this.createBlankRow();
                    this.addNewUserDatasource();
                }
            });
    }

    private loadInvoices(invoiceId : number) {
        this.isLoaded = true;
		this.invoiceService.findAllInvoicesDetails(invoiceId).subscribe((data: InvoiceDetail[]) => {
          this.invoice_details_list = data;
          this.invoice_details_list.sort(function (obj1, obj2) {
              // Descending: first id less than the previous
              return obj2.id - obj1.id;
          });
          this.isLoaded = false;
          this.dataSourceInvoiceDetailsList = new MatTableDataSource(this.invoice_details_list);
          this.dataSourceAddUser = new MatTableDataSource(this.addNewUser);
          this.dataSourceInvoiceDetailsList.sort = this.sort;
          this.dataSourceInvoiceDetailsList.paginator = this.paginator;
      },
          error => {
              alert("Error: " + error.name);
              this.isLoaded = false;
          }
      );
    }

    /*
    deleteUserForDialog(invoiceDetail: InvoiceDetail) {
        this.serv.deleteUser(invoiceDetail.id).subscribe(data => {
            this.statusMessage = 'InvoiceDetail ' + invoiceDetail.Name + ' is deleted',
                this.openSnackBar(this.statusMessage, "Success");
            this.loadInvoices();
        })
    }
    */

editInvoice(invoiceDetail: InvoiceDetail) {
    this.invoiceService.updateInvoiceDetail(invoiceDetail,invoiceDetail.id).subscribe(data => {
        this.statusMessage = 'InvoiceDetail ' + invoiceDetail.id + ' is updated',
        this.openSnackBar(this.statusMessage, "Success");
        this.loadInvoices(this.invoiceDetail.invoiceId);
    },
        error => {
            this.openSnackBar(error.statusText, "Error");
        }
    );
}

saveUser(invoiceDetail: InvoiceDetail ) {
    alert(invoiceDetail.productId);
    if(invoiceDetail.productId == null)
    {
        this.openSnackBar("Please select Product", "Error");
        return false;
    }
    else if(this.inv.customerId == null) {
        this.openSnackBar("Please select Customer", "Error");
        return false;
    }
    else if(this.inv.paymentModeId == null)
    {
        this.openSnackBar("Please select Payment Mode", "Error");
        return false;
    }
    else if(this.inv.accountId == null)
    {
        this.openSnackBar("Please select Account", "Error");
        return false;
    }
    else{
        if (invoiceDetail.productId != null ) {
        
            this.invoice2.id = this.inv.id;
            this.invoice2.name = this.inv.name;
            this.invoice2.invoiceNo = this.inv.invoiceNo;
            this.invoice2.invoiceDate = this.inv.invoiceDate;
            this.invoice2.billAmt = this.inv.billAmt;
            this.invoice2.roundAmt = this.inv.roundAmt;
            this.invoice2.invoiceAmt = this.inv.invoiceAmt;
    
            this.invoice2.discountAmt = this.inv.discountAmt;
            this.invoice2.taxAmt = this.inv.taxAmt;
            this.invoice2.amountPaid = this.inv.amountPaid;
    
            this.invoice2.amountDue = this.inv.amountDue;
            this.invoice2.customerId = this.inv.customerId;
            this.invoice2.accountId = this.inv.accountId;
            this.invoice2.paymentModeId = this.inv.paymentModeId;
            this.invoice2.chequeNo = this.inv.chequeNo;
            this.invoice2.bankName = this.inv.bankName;
    
            this.invoice2.invoiceDetails.push(invoiceDetail);
    
            this.invoiceService.save(this.invoice2).subscribe(data => {
                this.showTable = false;
                this.openSnackBar(this.statusMessage, "Success");
                this.loadInvoices(this.invoice2.id);
            },
                error => {
                    this.showTable = false;
                    this.openSnackBar(error.statusText, "Error");
                }
            );
        }
    }
    
}

updateInvoice(invoiceDetail: InvoiceDetail ) 
{
    this.invoice2.id = this.inv.id;
    this.invoice2.name = this.inv.name;
    this.invoice2.invoiceNo = this.inv.invoiceNo;
    this.invoice2.invoiceDate = this.inv.invoiceDate;
    this.invoice2.billAmt = this.inv.billAmt;
    this.invoice2.roundAmt = this.inv.roundAmt;
    this.invoice2.invoiceAmt = this.inv.invoiceAmt;

    this.invoice2.discountAmt = this.inv.discountAmt;
    this.invoice2.taxAmt = this.inv.taxAmt;
    this.invoice2.amountPaid = this.inv.amountPaid;

    this.invoice2.amountDue = this.inv.amountDue;
    this.invoice2.customerId = this.inv.customerId;
    this.invoice2.accountId = this.inv.accountId;
    this.invoice2.paymentModeId = this.inv.paymentModeId;
    this.invoice2.chequeNo = this.inv.chequeNo;
    this.invoice2.bankName = this.inv.bankName;

    this.invoice2.invoiceDetails.push(invoiceDetail);

    this.invoiceService.updateInvoiceDetailEntry(this.invoice2,this.invoice2.id ).subscribe(data => {
        this.showTable = false;
        this.openSnackBar(this.statusMessage, "Success");
        this.loadInvoices(this.invoice2.id);
    },
        error => {
            this.showTable = false;
            this.openSnackBar(error.statusText, "Error");
        }
    );
}

show() 
{
    this.showTable = true;
    this.addNewUser = [{
         id: 0,productId: null ,preProductId: null , hsnCode: null, rate: null, uomId: null,quantity : 0,preQuantity : 0,totalPrice : 0.00,discPer : 0.00,discAmt : 0.00,taxableAmt : 0.00,sgstPer : 0.00,sgstAmt : 0.00,cgstPer : 0.00,cgstAmt : 0.00,igstPer : 0.00,igstAmt : 0.00,invoiceId: null }
    ];
   if(this.dataSourceInvoiceDetailsList != undefined && this.dataSourceInvoiceDetailsList != null)
   {
       this.createBlankRow();
       this.addNewUserDatasource();
       this.loadInvoices(this.inv.id);
   }

    /*
    if(this.dataSourceInvoiceDetailsList != null && this.dataSourceInvoiceDetailsList != undefined)
    {
        alert(this.dataSourceInvoiceDetailsList.data);
        this.createBlankRow();
        this.addNewUserDatasource();
        this.loadInvoices(this.inv.id);
    }
    */

}

private addNewUserDatasource()
{
    //this.invoiceDetail.invoiceId.id = 0.00;
    //this.invoiceDetailsList.push(this.invoiceDetail);
    this.invoiceDetailsList.push(this.invoiceDetail);
    //this.dataSourceAddUser = new MatTableDataSource();
    //this.inv.invoiceDetails = this.invoiceDetailsList;
    this.dataSourceAddUser = new MatTableDataSource(this.invoiceDetailsList);
}

private createBlankRow() 
{
    this.businessId =1;
    /* Creating INvoice Detail Object */
    this.invoiceDetail = new InvoiceDetail();
    this.invoiceDetail.preProductId = null;
    this.invoiceDetail.productId = null;
    this.invoiceDetail.rate = 0.00;
    this.invoiceDetail.hsnCode = null;
    this.invoiceDetail.uomId = null;
    this.invoiceDetail.quantity = 0;
    this.invoiceDetail.preQuantity = 0;
    this.invoiceDetail.totalPrice = 0.00;
    this.invoiceDetail.discPer = 0.00;
    this.invoiceDetail.discAmt = 0.00;
    this.invoiceDetail.taxableAmt = 0.00;
    this.invoiceDetail.sgstPer = 0.00;
    this.invoiceDetail.sgstAmt = 0.00;
    this.invoiceDetail.cgstPer = 0.00;
    this.invoiceDetail.cgstAmt = 0.00;
    this.invoiceDetail.igstPer = 0.00;
    this.invoiceDetail.igstAmt = 0.00;

    this.invoiceDetail.invoiceId = this.inv.id;
}

save(){  
    console.log("save method called invoice Details="+this.inv);

    this.invoice.id = this.inv.id;
    this.invoice.invoiceDate = this.inv.invoiceDate;
    this.invoice.name = this.inv.name;
    this.invoice.invoiceNo = this.inv.invoiceNo;
    this.invoice.billAmt = this.inv.billAmt;
    this.invoice.roundAmt = this.inv.roundAmt;
    this.invoice.invoiceAmt = this.inv.invoiceAmt;

    this.invoice.discountAmt = this.inv.discountAmt;
    this.invoice.taxAmt = this.inv.taxAmt;
    this.invoice.amountPaid = this.inv.amountPaid;

    this.invoice.amountDue = this.inv.amountDue;
    this.invoice.customerId = this.inv.customerId;
    this.invoice.accountId = this.inv.accountId;
    this.invoice.paymentModeId = this.inv.paymentModeId;
    this.invoice.chequeNo = this.inv.chequeNo;
    this.invoice.bankName = this.inv.bankName;


      this.invoiceService.update(this.invoice,this.invoice.id).subscribe(data => {
        this.statusMessage = 'Invoice ' + this.invoice.name + ' is added',
        this.showTable = false;
        this.openSnackBar(this.statusMessage, "Success");
        this.loadInvoices(this.invoice.id);
    },
        error => {
            this.showTable = false;
            this.openSnackBar(error.statusText, "Error");
        }
    );
}


    customerChange(value) {
        this.customerService.get(value).subscribe((customer_data : Customer)=>{
            //element.productId.id = value;
            this.inv.customerId  =  customer_data.id;
            this.customer_state   =   customer_data.state.id;
            console.log("customer id="+this.inv.customerId);
            console.log("customer_state="+this.customer_state);

            },(error)=>{
            console.log(error);
        })

        this.businessService.get(this.businessId).subscribe((business_data : Business)=>{
            //element.productId.id = value;
            this.business_state     =  business_data.state.id;
            console.log("business_state ="+this.business_state);

            },(error)=>{
            console.log(error);
        })
    }

    change_product(element,value) 
    {
        if(element.productId == 'null')
        {
            element.rate        =   0.00;
            element.uomId       =   null;
            element.hsnCode     =   null;
            element.discPer     =   0.00;
            element.quantity    =   0.00;
            element.totalPrice  =   0.00;
            element.discAmt     =   0.00;
            element.taxableAmt  =   0.00;
            element.sgstPer     =   0.00;
            element.sgstAmt     =   0.00;
            element.cgstPer     =   0.00;
            element.cgstAmt     =   0.00;
            element.igstPer     =   0.00;
            element.igstAmt     =   0.00;


            this.inv.taxAmt     =  this.inv.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;
            this.inv.billAmt    =   this.inv.billAmtHid + element.taxableAmt ;
            this.inv.invoiceAmt = (this.inv.invoiceAmtHid + this.inv.billAmt) - this.inv.billAmtHid ;
            //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
            this.inv.amountPaid =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
            //this.inv.amountPaidHid =  this.inv.amountPaid; 
            this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
            //this.inv.amountDueHid = this.inv.amountDue;
            alert("Please select valid Product");
        }
        else
        {
            this.productService.get(value).subscribe((product_data : Product)=>{
                //element.productId.id = value;
                element.rate        =   product_data.salePrice;
                element.uomId       =   product_data.uom.id;
                element.hsnCode     =   product_data.code;
                element.discPer     =   product_data.discountPer;
                element.quantity    =   1.00;
                element.totalPrice  =   element.rate * element.quantity;
                element.discAmt     =   0.00;
                element.taxableAmt  =   element.totalPrice - element.discAmt;
    
                if(this.customer_state == this.business_state)
                {
                    element.sgstPer     =   product_data.saleTaxId.percentage / 2;
                    element.sgstAmt     =   (element.taxableAmt / 100 ) * element.sgstPer;
                    element.cgstPer     =   product_data.saleTaxId.percentage / 2;
                    element.cgstAmt     =   (element.taxableAmt / 100 ) * element.cgstPer;
                    element.igstPer     =   0.00;
                    element.igstAmt     =   0.00;
                }
                else
                {
                    element.sgstPer     =   0.00;
                    element.sgstAmt     =   0.00;
                    element.cgstPer     =   0.00;
                    element.cgstAmt     =   0.00;
                    element.igstPer     =   product_data.saleTaxId.percentage;
                    element.igstAmt     =   (element.taxableAmt / 100 ) * element.igstPer;
                    
                }
                
                this.inv.taxAmt     =  this.inv.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;

                this.inv.billAmt = this.inv.billAmtHid + element.taxableAmt ;
                this.inv.invoiceAmt = (this.inv.invoiceAmtHid + this.inv.billAmt) - this.inv.billAmtHid ;
                //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
                this.inv.amountPaid =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
                //this.inv.amountPaidHid =  this.inv.amountPaid; 
                this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
                //this.inv.amountDueHid = this.inv.amountDue;
    
                },(error)=>{
                console.log(error);
            })
        }
        
    }

    onQuanityChange(element,value) {
        console.log('quantity change='+value);
        if(element.id != null && element.id != 0 && element.id != '')
        {
            this.invoiceService.getInvoiceDetail(element.id).subscribe((invoice_detail_data : InvoiceDetail)=>{
                
                //element.productId.id = value;
                
                this.pre_taxableAmt     =   invoice_detail_data.taxableAmt;
                this.pre_sgstAmt        =   invoice_detail_data.sgstAmt;
                this.pre_cgstAmt        =   invoice_detail_data.cgstAmt;
                this.pre_igstAmt        =   invoice_detail_data.igstAmt;

                let bill_amt            =   element.rate * value;
                element.totalPrice      =   bill_amt;
                element.quantity        =   value;

                element.discAmt         =   0.00;
                element.taxableAmt      =   element.totalPrice - element.discAmt;
                element.sgstAmt         =   (element.taxableAmt / 100 ) * element.sgstPer;
                element.cgstAmt         =   (element.taxableAmt / 100 ) * element.cgstPer;
                element.igstAmt         =   (element.taxableAmt / 100 ) * element.igstPer;

                this.inv.taxAmt         =  this.inv.taxAmtHid + (element.sgstAmt + element.cgstAmt + element.igstAmt) - (this.pre_sgstAmt + this.pre_cgstAmt + this.pre_igstAmt);    

                this.inv.billAmt        = (this.inv.billAmtHid - this.pre_taxableAmt) + element.taxableAmt ;
                
                this.inv.invoiceAmt     = (this.inv.invoiceAmtHid + this.inv.billAmt) - this.inv.billAmtHid ;
                //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
                this.inv.amountPaid     =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
                //this.inv.amountPaidHid =  this.inv.amountPaid; 
                this.inv.amountDue      = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
                //this.inv.amountDueHid = this.inv.amountDue;


                },(error)=>{
                console.log(error);
            })
        }
        else
        {

            let bill_amt        =   element.rate * value;
            element.totalPrice  =   bill_amt;
            element.quantity    =   value;

            element.discAmt     =   0.00;
            element.taxableAmt  =   element.totalPrice - element.discAmt;
            element.sgstAmt     =   (element.taxableAmt / 100 ) * element.sgstPer;
            element.cgstAmt     =   (element.taxableAmt / 100 ) * element.cgstPer;
            element.igstAmt     =   (element.taxableAmt / 100 ) * element.igstPer;

            this.inv.taxAmt     =   this.inv.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;

            this.inv.billAmt    =   this.inv.billAmtHid + element.taxableAmt ;
            this.inv.invoiceAmt =   (this.inv.invoiceAmtHid + this.inv.billAmt) - this.inv.billAmtHid ;
            //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
            this.inv.amountPaid =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
            //this.inv.amountPaidHid =  this.inv.amountPaid; 
            this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
            //this.inv.amountDueHid = this.inv.amountDue;
        }
        
    }


    onDiscountChange(element,value) 
    {
        element.discPer     =   value;
        element.discAmt     =   (element.totalPrice / 100 ) * value;
        element.taxableAmt  =   element.totalPrice - element.discAmt;
        
        element.sgstAmt     =   (element.taxableAmt / 100 ) * element.sgstPer;
        element.cgstAmt     =   (element.taxableAmt / 100 ) * element.cgstPer;
        element.igstAmt     =   (element.taxableAmt / 100 ) * element.igstPer;

        this.inv.taxAmt     =  this.inv.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;

        this.inv.billAmt = this.inv.billAmtHid + element.taxableAmt ;
        this.inv.invoiceAmt = (this.inv.invoiceAmtHid + this.inv.billAmt) - this.inv.billAmtHid ;
        //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
        this.inv.amountPaid =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
        //this.inv.amountPaidHid =  this.inv.amountPaid; 
        this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
        //this.inv.amountDueHid = this.inv.amountDue;

    }

    onAmountPaidChange(value) 
    {
        alert('invoiceAmt'+this.inv.invoiceAmt);
        alert('amountPaid'+value);
        alert('amountDue'+this.inv.amountDue);
        alert('amountDueHid'+this.inv.amountDueHid);
        this.inv.amountDue = this.inv.invoiceAmt - value;
    }
    
    cancel(value) {
        /*
        this.inv.billAmt = this.inv.billAmt - value ;
        this.inv.billAmtHid  = this.inv.billAmt;
        this.inv.invoiceAmt = ((this.inv.invoiceAmt + this.inv.billAmt) - value) ;
        this.inv.invoiceAmtHid = this.inv.invoiceAmt;

        this.inv.amountPaid = ( this.inv.invoiceAmt + this.inv.amountPaid ) - this.inv.amountPaidHid ;
        this.inv.amountPaidHid =  this.inv.amountPaid; 
        this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
        this.inv.amountDueHid = this.inv.amountDue;
        */

       this.inv.billAmt = this.inv.billAmt - value ;
       this.inv.invoiceAmt = (( (this.inv.invoiceAmt + this.inv.billAmt )- this.inv.billAmtHid ) - value) ;
        //this.inv.invoiceAmtHid = this.inv.invoiceAmt;
        this.inv.amountPaid =  (this.inv.invoiceAmt + this.inv.amountPaidHid )- this.inv.invoiceAmtHid;
        //this.inv.amountPaidHid =  this.inv.amountPaid; 
        this.inv.amountDue = (this.inv.invoiceAmt - this.inv.amountPaid )  - this.inv.amountDueHid ;
        //this.inv.amountDueHid = this.inv.amountDue;

        this.showTable = false;
    }

    //snackBar
    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
            duration: 3000,
        });
    }

    //material dialog
    openDialog(element): void {
        const dialogRef = this.dialog.open(DialogOverviewExampleDialogComponent, {
            width: '250px',
            data: element,
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
            if (result == "Confirm") {
                //this.deleteUserForDialog(element);
            }
        });
    }

    //   Form field with error messages 
    productId = new FormControl('', [Validators.required]);

    getErrorMessage() {
        return this.productId.hasError('required') ? 'You select a value' :
            this.productId.hasError('productId') ? 'Not a valid Product' : '';
    }

    rate        = new FormControl('', [Validators.required]);
    rateGetErrorMessage() {
        return this.rate.hasError('required') ? 'You Enter a Rate value' :
            this.rate.hasError('rate') ? 'Not a valid Rate' : '';
    }

}