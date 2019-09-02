import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { PurchaseReportService}  from '../../../shared_service/purchase-report.service';
import{PurchaseHistory}  from '../purchase-history';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.scss']
})
export class PurchaseHistoryComponent implements OnInit {

  public purchase_history_list:PurchaseHistory[];

  constructor(private purchaseReportService: PurchaseReportService,private router: Router) { }

  ngOnInit() {
    this.purchaseReportService.getAll().subscribe((purchase_history_list)=>{
      console.log(purchase_history_list);
      this.purchase_history_list=purchase_history_list;
      },(error)=>{
        console.log(error);
      })
  }

  gotoList() {
    this.router.navigate(['purchasereport/purchase-history']);
    //this.router.navigate(['Uom-list'], {relativeTo: this.route});
  }

}
