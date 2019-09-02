import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PurchasereportRoutingModule } from './purchasereport-routing.module';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';


@NgModule({
  declarations: [PurchaseHistoryComponent],
  imports: [
    CommonModule,
    PurchasereportRoutingModule
  ]
})
export class PurchasereportModule { }
