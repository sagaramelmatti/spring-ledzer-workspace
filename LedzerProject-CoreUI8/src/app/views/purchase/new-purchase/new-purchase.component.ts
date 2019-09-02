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
import  { PurchaseService}  from '../../../shared_service/purchase.service';
import 	{Purchase}  from '../purchase';
import 	{PurchaseDetail}  from '../purchaseDetail';
import 	{Product}  from '../../product/product';
import 	{Uom}  from '../../uom/uom';
import 	{Supplier}  from '../../supplier/supplier';
import 	{Business}  from '../../business/business';
import 	{Tax}  from '../../tax/tax';
import  { Category } from '../../category/category';
import 	{State}  from '../../customer/state';
import { PaymentMode } from '../../invoice/paymentMode';
import  { UserAccount } from '../../accounts/user-account';
import  { CommonService  }  from '../../../shared_service/common.service';
import  { SupplierService  }  from '../../../shared_service/supplier.service';
import  { BusinessService  }  from '../../../shared_service/business.service';
import  { TaxService  }  from '../../../shared_service/tax.service';
import  { CategoryService  }  from '../../../shared_service/category.service';
import  { UomService  }  from '../../../shared_service/uom.service';
import  { AccountsService  }  from '../../../shared_service/accounts.service';
import  { ProductService  }  from '../../../shared_service/product.service';
import { PurchaseDetailDataSource} from "../purchaseDetail.dataSource";
import {    catchError, finalize} from "rxjs/operators";

@Component({
  selector: 'app-new-purchase',
  templateUrl: './new-purchase.component.html',
  styleUrls: ['./new-purchase.component.scss']
})
export class NewPurchaseComponent implements OnInit {

sub: Subscription;
pur: Purchase = new Purchase();
uom: Uom = new Uom();
tax: Tax = new Tax();
purchaseDetail: PurchaseDetail;

/* Objects to create Drop down */
public tax_list:Tax[];
public uom_list:Uom[];
public supplier_list:Supplier[];
public payment_mode_list:PaymentMode[];
public account_list:UserAccount[];
public state_list:State[];
public product_list:Product[];
public purchase_list:Purchase[];

purchaseDetailsList: PurchaseDetail[] = [];
businessId : number;
supplier_state : number;
business_state : number;

/* Object Declaration */
purchase: Purchase = new Purchase();
purchase3: Purchase = new Purchase();

purchase_details_list: Array<PurchaseDetail>;
dataSourceAddUser: any;
dataSourcePurchaseDetailsList: any;

showTable: boolean;
statusMessage: string;
isLoaded: boolean = true;

/* purchase details object data */
pre_taxableAmt  : number;
pre_sgstAmt     : number;
pre_cgstAmt     : number;
pre_igstAmt     : number;

@ViewChild('paginator', { static: false, }) paginator: MatPaginator;
@ViewChild('sort', { static: false, }) sort: MatSort;
@ViewChild('input',{ static: false, }) input: ElementRef;
    
    /*  purchase details object data End */

addNewUser: PurchaseDetail[] = [
    { id: 0,productId: null ,preProductId: null , hsnCode: null, rate: null, uomId: null,quantity : 0,preQuantity : 0,totalPrice : 0.00,discPer : 0.00,discAmt : 0.00,taxableAmt : 0.00,sgstPer : 0.00,sgstAmt : 0.00,cgstPer : 0.00,cgstAmt : 0.00,igstPer : 0.00,igstAmt : 0.00,purchaseId: null }
];
displayedColumnsUsers: string[] = ['id', 'productId','preProductId','rate', 'hsnCode',  'uomId','quantity', 'preQuantity', 'totalPrice', 'discPer','discAmt', 'taxableAmt', 'sgstPer','sgstAmt','cgstPer','cgstAmt','igstPer','igstAmt','purchaseId', 'Change', 'Delete'];
displayedColumnsAddUser: string[] = ['productId','preProductId','hsnCode',  'rate',  'uomId','quantity', 'preQuantity', 'totalPrice', 'discPer','discAmt', 'taxableAmt', 'sgstPer','sgstAmt','cgstPer','cgstAmt','igstPer','igstAmt','purchaseId', 'Save', 'Cancel'];
    
constructor(private route: ActivatedRoute,private purchaseService: PurchaseService, private commonService:CommonService,private supplierService:SupplierService,private taxService:TaxService,private categoryService:CategoryService,
    private uomService:UomService,private accountsService:AccountsService,private productService:ProductService,private businessService:BusinessService, 
        public dialog: MatDialog, public snackBar: MatSnackBar) {

            this.purchase_details_list = new Array<PurchaseDetail>();
}

ngOnInit() {
    this.taxService.getAll().subscribe((tax_list)=>{
        this.tax_list=tax_list;
        },(error)=>{
            console.log(error);
        }),
        this.uomService.getAll().subscribe((uom_list)=>{
            this.uom_list=uom_list;
            },(error)=>{
            console.log(error);
        }),
        this.supplierService.getAll().subscribe((supplier_list)=>{
            this.supplier_list=supplier_list;
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
        this.purchaseService.getAll().subscribe((purchase_list)=>{
        this.purchase_list=purchase_list;
        },(error)=>{
            console.log(error);
        }),   

        this.isLoaded = true;
        this.sub = this.route.params.subscribe(params => {
            const id = params['id'];
            if (id) 
            {
                this.purchaseService.get(id).subscribe((purchase_obj: Purchase) => {
                if (purchase_obj) 
                {
                    this.pur = purchase_obj;
                    this.pur.purchaseDate = purchase_obj.purchaseDate;
                    console.log("purchase date="+this.pur.purchaseDate)
                    this.loadPurchases(this.pur.id);
                    
                } else {
                    console.log(`Purchase with id '${purchase_obj.id}' not found, returning to list`);
                    //this.gotoList();
                }
                });
            }
            else
            {
                console.log("this pur id is null");
                this.accountsService.getDefaultAccount().subscribe((default_acc_id : number)=>{
                    this.pur.accountId = default_acc_id;
                    },
                    (error)=>{
                        console.log(error);
                });
                this.purchaseService.getNextPurchaseId().subscribe((pur_id : number)=>{
                if(pur_id == null || pur_id == undefined)
                {
                    pur_id =1;
                }
                console.log("next id ="+pur_id);
                this.pur.id = pur_id;
                this.pur.name = "PUR000"+pur_id;
                this.pur.purchaseNo = pur_id;

                /* creating Empty Objects */
                this.tax.id = null;

                /*  Creating Purchase Details object  for new object */
                //this.purchaseDetail.id = null;
                this.pur.purchaseDate = null;
                this.pur.supplierId = null;
                this.pur.voucher = null;
                this.pur.amountPaid = 0.00;
                this.pur.amountPaidHid = 0.00;
                this.pur.amountDue = 0.00;
                this.pur.amountDueHid = 0.00;
                this.pur.taxAmt = 0.00;
                this.pur.taxAmtHid = 0.00;
                this.pur.discountAmt = 0.00;
                this.pur.billAmt = 0.00;
                this.pur.billAmtHid = 0.00;
                //this.purchaseId.addDiscAmt = 0.00;
                this.pur.taxAmt = 0.00;
                this.pur.roundAmt = 0.00;
                this.pur.purchaseAmt = 0.00;
                this.pur.purchaseAmtHid = 0.00;
                this.pur.accountId = null;
                this.pur.paymentModeId = 1;
                this.pur.chequeNo = null;
                this.pur.bankName = null;
                },
                (error)=>{
                    console.log(error);
                })
                this.createBlankRow();
                this.addNewUserDatasource();
            }
        });
    }
    
    private loadPurchases(purchaseId : number) {
		this.isLoaded = true;
		this.purchaseService.findAllPurchasesDetails(purchaseId).subscribe((data: PurchaseDetail[]) => {
          this.purchase_details_list = data;
          this.purchase_details_list.sort(function (obj1, obj2) {
              // Descending: first id less than the previous
              return obj2.id - obj1.id;
          });
          console.log(this.purchase_details_list);
          this.isLoaded = false;
          this.dataSourcePurchaseDetailsList = new MatTableDataSource(this.purchase_details_list);
          this.dataSourceAddUser = new MatTableDataSource(this.addNewUser);
          this.dataSourcePurchaseDetailsList.sort = this.sort;
          this.dataSourcePurchaseDetailsList.paginator = this.paginator;
      },
          error => {
              alert("Error: " + error.name);
              this.isLoaded = false;
          }
      );
    }

    /*
    deleteUserForDialog(purchaseDetail: PurchaseDetail) {
        this.serv.deleteUser(purchaseDetail.id).subscribe(data => {
            this.statusMessage = 'PurchaseDetail ' + purchaseDetail.Name + ' is deleted',
                this.openSnackBar(this.statusMessage, "Success");
            this.loadPurchases();
        })
    }
    */

   editPurchase(purchaseDetail: PurchaseDetail) {
    this.purchaseService.updatePurchaseDetail(purchaseDetail,purchaseDetail.id).subscribe(data => {
        this.statusMessage = 'PurchaseDetail ' + purchaseDetail.id + ' is updated',
        this.openSnackBar(this.statusMessage, "Success");
        this.loadPurchases(this.purchaseDetail.purchaseId);
    },
        error => {
            this.openSnackBar(error.statusText, "Error");
        }
    );
}

saveUser(purchaseDetail: PurchaseDetail ) {
    if(purchaseDetail.productId == null)
    {
        this.openSnackBar("Please select Product", "Error");
        return false;
    }
    else if(this.pur.supplierId == null) {
        this.openSnackBar("Please select Supplier", "Error");
        return false;
    }
    else if(this.pur.paymentModeId == null)
    {
        this.openSnackBar("Please select Payment Mode", "Error");
        return false;
    }
    else if(this.pur.accountId == null)
    {
        this.openSnackBar("Please select Account", "Error");
        return false;
    }
    else{
        if (purchaseDetail.productId != null ) {
            this.purchase.id = this.pur.id;
            this.purchase.name = this.pur.name;
            this.purchase.purchaseNo = this.pur.purchaseNo;
            this.purchase.purchaseDate = this.pur.purchaseDate;
            this.purchase.voucher = this.pur.voucher;
            this.purchase.billAmt = this.pur.billAmt;
            this.purchase.roundAmt = this.pur.roundAmt;
            this.purchase.purchaseAmt = this.pur.purchaseAmt;

            this.purchase.discountAmt = this.pur.discountAmt;
            this.purchase.taxAmt = this.pur.taxAmt;
            this.purchase.amountPaid = this.pur.amountPaid;

            this.purchase.amountDue = this.pur.amountDue;
            this.purchase.supplierId = this.pur.supplierId;
            this.purchase.accountId = this.pur.accountId;
            this.purchase.paymentModeId = this.pur.paymentModeId;
            this.purchase.chequeNo = this.pur.chequeNo;
            this.purchase.bankName = this.pur.bankName;

            this.purchase.purchaseDetails.push(purchaseDetail);
            this.purchaseService.save(this.purchase).subscribe(data => {
                this.showTable = false;
                this.openSnackBar(this.statusMessage, "Success");
                this.loadPurchases(this.purchase.id);
            },
                error => {
                    this.showTable = false;
                    this.openSnackBar(error.statusText, "Error");
                }
            );
        }
    }
}

updatePurchase(purchaseDetail: PurchaseDetail ) {
    this.purchase.id = this.pur.id;
    this.purchase.name = this.pur.name;
    this.purchase.purchaseNo = this.pur.purchaseNo;
    this.purchase.purchaseDate = this.pur.purchaseDate;
    this.purchase.voucher = this.pur.voucher;
    this.purchase.billAmt = this.pur.billAmt;
    this.purchase.roundAmt = this.pur.roundAmt;
    this.purchase.purchaseAmt = this.pur.purchaseAmt;

    this.purchase.discountAmt = this.pur.discountAmt;
    this.purchase.taxAmt = this.pur.taxAmt;
    this.purchase.amountPaid = this.pur.amountPaid;

    this.purchase.amountDue = this.pur.amountDue;
    this.purchase.supplierId = this.pur.supplierId;
    this.purchase.accountId = this.pur.accountId;
    this.purchase.paymentModeId = this.pur.paymentModeId;
    this.purchase.chequeNo = this.pur.chequeNo;
    this.purchase.bankName = this.pur.bankName;

    this.purchase.purchaseDetails.push(purchaseDetail);

    this.purchaseService.updatePurchaseDetailEntry(this.purchase,this.purchase.id ).subscribe(data => {
        this.showTable = false;
        this.openSnackBar(this.statusMessage, "Success");
        this.loadPurchases(this.purchase.id);
    },
        error => {
            this.showTable = false;
            this.openSnackBar(error.statusText, "Error");
        }
    );
}

show() {
    this.showTable = true;
    console.log("show method called ");
    this.addNewUser = [{ id: 0,productId: null ,preProductId: null , hsnCode: null, rate: null, uomId: null,quantity : 0,preQuantity : 0,totalPrice : 0.00,discPer : 0.00,discAmt : 0.00,taxableAmt : 0.00,sgstPer : 0.00,sgstAmt : 0.00,cgstPer : 0.00,cgstAmt : 0.00,igstPer : 0.00,igstAmt : 0.00,purchaseId: null }];
    if(this.dataSourcePurchaseDetailsList != null && this.dataSourcePurchaseDetailsList != undefined)
    {
        alert('dataSourceInvoiceDetailsList is not null');
        this.createBlankRow();
        this.addNewUserDatasource();
        this.loadPurchases(this.pur.id);
    }
}
private addNewUserDatasource()
{
    this.purchaseDetailsList.push(this.purchaseDetail);
    this.dataSourceAddUser = new MatTableDataSource(this.purchaseDetailsList);
}
private createBlankRow() 
{
        this.businessId =1;
        /* Creating INvoice Detail Object */
        this.purchaseDetail = new PurchaseDetail()
        this.purchaseDetail.productId = null;
        this.purchaseDetail.rate = 0.00;
        this.purchaseDetail.hsnCode = null;
        this.purchaseDetail.uomId = null;
        this.purchaseDetail.quantity = 0;
        this.purchaseDetail.totalPrice = 0.00;
        this.purchaseDetail.discPer = 0.00;
        this.purchaseDetail.discAmt = 0.00;
        this.purchaseDetail.taxableAmt = 0.00;
        this.purchaseDetail.sgstPer = 0.00;
        this.purchaseDetail.sgstAmt = 0.00;
        this.purchaseDetail.cgstPer = 0.00;
        this.purchaseDetail.cgstAmt = 0.00;
        this.purchaseDetail.igstPer = 0.00;
        this.purchaseDetail.igstAmt = 0.00;

        this.purchaseDetail.purchaseId = this.pur.id;
}
save(){  
    console.log("save method called purchase Details="+this.pur);

    this.purchase3.id = this.pur.id;
    this.purchase3.purchaseDate = this.pur.purchaseDate;
    this.purchase3.voucher = this.pur.voucher;
    this.purchase3.name = this.pur.name;
    this.purchase3.purchaseNo = this.pur.purchaseNo;
    this.purchase3.billAmt = this.pur.billAmt;
    this.purchase3.roundAmt = this.pur.roundAmt;
    this.purchase3.purchaseAmt = this.pur.purchaseAmt;

    this.purchase3.discountAmt = this.pur.discountAmt;
    this.purchase3.taxAmt = this.pur.taxAmt;
    this.purchase3.amountPaid = this.pur.amountPaid;

    this.purchase3.amountDue = this.pur.amountDue;
    this.purchase3.supplierId = this.pur.supplierId;
    this.purchase3.accountId = this.pur.accountId;
    this.purchase3.paymentModeId = this.pur.paymentModeId;
    this.purchase3.chequeNo = this.pur.chequeNo;
    this.purchase3.bankName = this.pur.bankName;


      this.purchaseService.update(this.purchase3,this.purchase3.id).subscribe(data => {
        this.statusMessage = 'Purchase ' + this.purchase3.name + ' is added',
        this.showTable = false;
        this.openSnackBar(this.statusMessage, "Success");
        this.loadPurchases(this.purchase3.id);
    },
        error => {
            this.showTable = false;
            this.openSnackBar(error.statusText, "Error");
        }
    );
}

    
supplierChange(value) {
    this.supplierService.get(value).subscribe((supplier_data : Supplier)=>{
        //element.productId.id = value;
        this.pur.supplierId  =  supplier_data.id;
        this.supplier_state   =   supplier_data.state.id;
        console.log("supplier id="+this.pur.supplierId);
        console.log("supplier_state="+this.supplier_state);

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


            this.pur.taxAmt     =  this.pur.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;
            this.pur.billAmt    =   this.pur.billAmtHid + element.taxableAmt ;
            this.pur.purchaseAmt = (this.pur.purchaseAmtHid + this.pur.billAmt) - this.pur.billAmtHid ;
            //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
            this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
            //this.pur.amountPaidHid =  this.pur.amountPaid; 
            this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
            //this.pur.amountDueHid = this.pur.amountDue;
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

            if(this.supplier_state == this.business_state)
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
                
            this.pur.taxAmt     =  this.pur.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;
            this.pur.billAmt = this.pur.billAmtHid + element.taxableAmt ;
            this.pur.purchaseAmt = (this.pur.purchaseAmtHid + this.pur.billAmt) - this.pur.billAmtHid ;
            //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
            this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
            //this.pur.amountPaidHid =  this.pur.amountPaid; 
            this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
            //this.pur.amountDueHid = this.pur.amountDue;
    
                },(error)=>{
                console.log(error);
            })
        }
}

onQuanityChange(element,value) {
    if(element.id != null && element.id != 0 && element.id != '')
    {
        this.purchaseService.getPurchaseDetail(element.id).subscribe((purchase_detail_data : PurchaseDetail)=>{
            
            //element.productId.id = value;

            this.pre_taxableAmt     =   purchase_detail_data.taxableAmt;
            this.pre_sgstAmt        =   purchase_detail_data.sgstAmt;
            this.pre_cgstAmt        =   purchase_detail_data.cgstAmt;
            this.pre_igstAmt        =   purchase_detail_data.igstAmt;

            let bill_amt        =   element.rate * value;
            element.totalPrice  =   bill_amt;
            element.quantity    =   value;

            element.discAmt     =   0.00;
            element.taxableAmt  =   element.totalPrice - element.discAmt;
            element.sgstAmt     =   (element.taxableAmt / 100 ) * element.sgstPer;
            element.cgstAmt     =   (element.taxableAmt / 100 ) * element.cgstPer;
            element.igstAmt     =   (element.taxableAmt / 100 ) * element.igstPer;

            this.pur.taxAmt     =  this.pur.taxAmtHid + (element.sgstAmt + element.cgstAmt + element.igstAmt) - (this.pre_sgstAmt + this.pre_cgstAmt + this.pre_igstAmt);    

            this.pur.billAmt = (this.pur.billAmtHid - this.pre_taxableAmt) + element.taxableAmt ;
            
            this.pur.purchaseAmt = (this.pur.purchaseAmtHid + this.pur.billAmt) - this.pur.billAmtHid ;
            //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
            this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
            //this.pur.amountPaidHid =  this.pur.amountPaid; 
            this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
            //this.pur.amountDueHid = this.pur.amountDue;


            },(error)=>{
            console.log(error);
        })
    }
    else{

        let bill_amt        =   element.rate * value;
        element.totalPrice  =   bill_amt;
        element.quantity    =   value;

        element.discAmt     =   0.00;
        element.taxableAmt  =   element.totalPrice - element.discAmt;
        element.sgstAmt     =   (element.taxableAmt / 100 ) * element.sgstPer;
        element.cgstAmt     =   (element.taxableAmt / 100 ) * element.cgstPer;
        element.igstAmt     =   (element.taxableAmt / 100 ) * element.igstPer;

        this.pur.taxAmt     =  this.pur.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;

        this.pur.billAmt = this.pur.billAmtHid + element.taxableAmt ;
        this.pur.purchaseAmt = (this.pur.purchaseAmtHid + this.pur.billAmt) - this.pur.billAmtHid ;
        //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
        this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
        //this.pur.amountPaidHid =  this.pur.amountPaid; 
        this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
        //this.pur.amountDueHid = this.pur.amountDue;
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

    this.pur.taxAmt     =  this.pur.taxAmtHid + element.sgstAmt + element.cgstAmt + element.igstAmt;

    this.pur.billAmt = this.pur.billAmtHid + element.taxableAmt ;
    this.pur.purchaseAmt = (this.pur.purchaseAmtHid + this.pur.billAmt) - this.pur.billAmtHid ;
    //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
    this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
    //this.pur.amountPaidHid =  this.pur.amountPaid; 
    this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
    //this.pur.amountDueHid = this.pur.amountDue;

}

    onAmountPaidChange(value) 
    {
        alert('purchaseAmt'+this.pur.purchaseAmt);
        alert('amountPaid'+value);
        alert('amountDue'+this.pur.amountDue);
        alert('amountDueHid'+this.pur.amountDueHid);
        this.pur.amountDue = this.pur.purchaseAmt - value;
    }
    
    cancel(value) {
       this.pur.billAmt = this.pur.billAmt - value ;
       this.pur.purchaseAmt = (( (this.pur.purchaseAmt + this.pur.billAmt )- this.pur.billAmtHid ) - value) ;
        //this.pur.purchaseAmtHid = this.pur.purchaseAmt;
        this.pur.amountPaid =  (this.pur.purchaseAmt + this.pur.amountPaidHid )- this.pur.purchaseAmtHid;
        //this.pur.amountPaidHid =  this.pur.amountPaid; 
        this.pur.amountDue = (this.pur.purchaseAmt - this.pur.amountPaid )  - this.pur.amountDueHid ;
        //this.pur.amountDueHid = this.pur.amountDue;
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
