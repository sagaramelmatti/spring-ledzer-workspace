import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ActivatedRoute} from "@angular/router";
import  { Subscription } from 'rxjs';
import  { InvoiceReportService}  from '../../../shared_service/invoice-report.service';
import{InvoiceHistory}  from '../invoice-history';

@Component({
  selector: 'app-invoice-history',
  templateUrl: './invoice-history.component.html',
  styleUrls: ['./invoice-history.component.scss']
})
export class InvoiceHistoryComponent implements OnInit {

  sub: Subscription;

  public invoice_sales_history_list:InvoiceHistory[];

  constructor(private route: ActivatedRoute,private invoiceReportService: InvoiceReportService,private router: Router) { }

  ngOnInit() {

    console.log(this.route.snapshot.url[0].path); 
    this.sub = this.route.params.subscribe(params => {
      if (this.route.snapshot.url[0].path == "print-invoice-history") 
      {
        this.invoiceReportService.getAllInvoiceHistoryReport();
        this.invoiceReportService.getAll().subscribe((invoice_sales_history_list)=>{
          console.log(invoice_sales_history_list);
          this.invoice_sales_history_list=invoice_sales_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else if(this.route.snapshot.url[0].path == "print-invoice-history-excel")
      {
        this.invoiceReportService.getAllInvoiceHistoryExcelReport();
          this.invoiceReportService.getAll().subscribe((invoice_sales_history_list)=>{
          console.log(invoice_sales_history_list);
          this.invoice_sales_history_list=invoice_sales_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else
      {
          this.invoiceReportService.getAll().subscribe((invoice_sales_history_list)=>{
            console.log(invoice_sales_history_list);
            this.invoice_sales_history_list=invoice_sales_history_list;
            },(error)=>{
              console.log(error);
            })
      }
    });
  }

  gotoList() {
    this.router.navigate(['invoicereport/invoice-sales-history']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }
}
