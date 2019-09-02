import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ActivatedRoute} from "@angular/router";
import  { Subscription } from 'rxjs';
import  { InvoiceReportService}  from '../../../shared_service/invoice-report.service';
import{CustomerWiseSaleHistory}  from '../customer-wise-sale-history';
import  { CustomerService  }  from '../../../shared_service/customer.service';
import 	{Customer}  from '../../customer/customer';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer-wise-sale-history',
  templateUrl: './customer-wise-sale-history.component.html',
  styleUrls: ['./customer-wise-sale-history.component.scss']
})
export class CustomerWiseSaleHistoryComponent implements OnInit {

  sub: Subscription;
  public customer_wise_sale_history_list:CustomerWiseSaleHistory[];
  public customer_list:Customer[];
  form: FormGroup;
  user: Observable<CustomerWiseSaleHistory>;
  customerId: number;
  fromDate: Date;
  toDate : Date;

  constructor(private route: ActivatedRoute,private invoiceReportService: InvoiceReportService,private router: Router,private customerService:CustomerService,private formBuilder: FormBuilder,) { }

  ngOnInit() {

    this.customerId = null;
    this.fromDate = null;
    this.toDate = null;

    console.log(this.route.snapshot.url[0].path); 
    this.customerService.getAll().subscribe((customer_list)=>{
      this.customer_list=customer_list;
      },(error)=>{
        console.log(error);
    }),

    this.form = this.formBuilder.group({
      customerId: [],
      fromDate: [],
      toDate: []
    });


    this.sub = this.route.params.subscribe(params => {

      this.invoiceReportService.getAllCustomerWiseSaleHistoryReport(this.customerId,this.fromDate,this.toDate).subscribe((customer_wise_sale_history_list)=>{
        console.log(customer_wise_sale_history_list);
        this.customer_wise_sale_history_list=customer_wise_sale_history_list;
        },(error)=>{
          console.log(error);
        })
      /*
      if (this.route.snapshot.url[0].path == "item-wise-sale-history-pdf") 
      {
        this.invoiceReportService.getAllItemWiseSaleHistoryReportPdf();
        this.invoiceReportService.getAllCustomerWiseSaleHistoryReport(this.customerId,this.fromDate,this.toDate).subscribe((customer_wise_sale_history_list)=>{
          console.log(customer_wise_sale_history_list);
          this.customer_wise_sale_history_list=customer_wise_sale_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else if(this.route.snapshot.url[0].path == "item-wise-sale-history-excel")
      {
        this.invoiceReportService.getAllItemWiseSaleHistoryReportExcel();
          this.invoiceReportService.getAllCustomerWiseSaleHistoryReport().subscribe((customer_wise_sale_history_list)=>{
          console.log(customer_wise_sale_history_list);
          this.customer_wise_sale_history_list=customer_wise_sale_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else
      {
          this.invoiceReportService.getAllCustomerWiseSaleHistoryReport().subscribe((customer_wise_sale_history_list)=>{
            console.log(customer_wise_sale_history_list);
            this.customer_wise_sale_history_list=customer_wise_sale_history_list;
            },(error)=>{
              console.log(error);
            })
      }
      */
    });
  }

  gotoList() {
    this.router.navigate(['invoicereport/item-wise-sale-history']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }

  submit() {
    if (this.form.valid) {
      console.log(this.form.value);
      this.customerId = this.form.value.customerId;
      this.fromDate = this.form.value.fromDate;
      this.toDate = this.form.value.toDate;

      this.invoiceReportService.getAllCustomerWiseSaleHistoryReport(this.form.value.customerId,this.form.value.fromDate,this.form.value.toDate);
      this.invoiceReportService.getAllCustomerWiseSaleHistoryReport(this.customerId,this.fromDate,this.toDate).subscribe((customer_wise_sale_history_list)=>{
        console.log(customer_wise_sale_history_list);
        this.customer_wise_sale_history_list=customer_wise_sale_history_list;
        },(error)=>{
          console.log(error);
        })
    }
  } 

  show() 
  {
    console.log('customerId'+this.form.value.customerId);
    this.customerId = this.form.value.customerId;
      this.fromDate = this.form.value.fromDate;
      this.toDate = this.form.value.toDate;

      this.invoiceReportService.getAllCustomerWiseSaleHistoryReportPdf(this.form.value.customerId,this.form.value.fromDate,this.form.value.toDate);
      this.invoiceReportService.getAllCustomerWiseSaleHistoryReport(this.customerId,this.fromDate,this.toDate).subscribe((customer_wise_sale_history_list)=>{
        console.log(customer_wise_sale_history_list);
        this.customer_wise_sale_history_list=customer_wise_sale_history_list;
        },(error)=>{
          console.log(error);
        })

  }

}
