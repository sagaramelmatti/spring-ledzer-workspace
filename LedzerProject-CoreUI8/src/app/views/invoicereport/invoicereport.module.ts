import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InvoicereportRoutingModule } from './invoicereport-routing.module';
import { InvoiceHistoryComponent } from './invoice-history/invoice-history.component';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ItemWiseSaleHistoryComponent } from './item-wise-sale-history/item-wise-sale-history.component';
import { CustomerWiseSaleHistoryComponent } from './customer-wise-sale-history/customer-wise-sale-history.component';


@NgModule({
  declarations: [InvoiceHistoryComponent, ItemWiseSaleHistoryComponent, CustomerWiseSaleHistoryComponent],
  imports: [
    CommonModule,
    InvoicereportRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class InvoicereportModule { }
