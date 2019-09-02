import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceHistoryComponent } from './invoice-history/invoice-history.component';
import { ItemWiseSaleHistoryComponent } from './item-wise-sale-history/item-wise-sale-history.component';
import { CustomerWiseSaleHistoryComponent } from './customer-wise-sale-history/customer-wise-sale-history.component';



const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'invoice-sales-history'
      },
      {
        path: 'invoice-sales-history',
        component: InvoiceHistoryComponent,
      },
      {
        path: 'print-invoice-history',
        component: InvoiceHistoryComponent
      },
      
      {
        path: 'print-invoice-history-excel',
        component: InvoiceHistoryComponent
      },
      {
        path: 'item-wise-sales-history',
        component: ItemWiseSaleHistoryComponent
      },
      {
        path: 'item-wise-sale-history-pdf',
        component: ItemWiseSaleHistoryComponent
      },
      {
        path: 'item-wise-sale-history-excel',
        component: ItemWiseSaleHistoryComponent
      },
      {
        path: 'customer-wise-sale-history',
        component: CustomerWiseSaleHistoryComponent
      },
      {
        path: 'customer-wise-sale-history-pdf',
        component: CustomerWiseSaleHistoryComponent
      },
      {
        path: 'customer-wise-sale-history-excel',
        component: CustomerWiseSaleHistoryComponent
      },
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoicereportRoutingModule { }
