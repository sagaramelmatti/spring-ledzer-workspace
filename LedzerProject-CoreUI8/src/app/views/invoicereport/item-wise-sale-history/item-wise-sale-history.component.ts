import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ActivatedRoute} from "@angular/router";
import  { Subscription } from 'rxjs';
import  { InvoiceReportService}  from '../../../shared_service/invoice-report.service';
import{ItemWiseSaleHistory}  from '../item-wise-sale-history';

@Component({
  selector: 'app-item-wise-sale-history',
  templateUrl: './item-wise-sale-history.component.html',
  styleUrls: ['./item-wise-sale-history.component.scss']
})
export class ItemWiseSaleHistoryComponent implements OnInit {

  sub: Subscription;

  public item_wise_sale_history_list:ItemWiseSaleHistory[];

  constructor(private route: ActivatedRoute,private invoiceReportService: InvoiceReportService,private router: Router) { }

  ngOnInit() {

    console.log(this.route.snapshot.url[0].path); 
    this.sub = this.route.params.subscribe(params => {
      if (this.route.snapshot.url[0].path == "item-wise-sale-history-pdf") 
      {
        this.invoiceReportService.getAllItemWiseSaleHistoryReportPdf();
        this.invoiceReportService.getAllItemWiseSaleHistoryReport().subscribe((item_wise_sale_history_list)=>{
          console.log(item_wise_sale_history_list);
          this.item_wise_sale_history_list=item_wise_sale_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else if(this.route.snapshot.url[0].path == "item-wise-sale-history-excel")
      {
        this.invoiceReportService.getAllItemWiseSaleHistoryReportExcel();
          this.invoiceReportService.getAllItemWiseSaleHistoryReport().subscribe((item_wise_sale_history_list)=>{
          console.log(item_wise_sale_history_list);
          this.item_wise_sale_history_list=item_wise_sale_history_list;
          },(error)=>{
            console.log(error);
          })
      }
      else
      {
          this.invoiceReportService.getAllItemWiseSaleHistoryReport().subscribe((item_wise_sale_history_list)=>{
            console.log(item_wise_sale_history_list);
            this.item_wise_sale_history_list=item_wise_sale_history_list;
            },(error)=>{
              console.log(error);
            })
      }
    });
  }

  gotoList() {
    this.router.navigate(['invoicereport/item-wise-sale-history']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }
}
