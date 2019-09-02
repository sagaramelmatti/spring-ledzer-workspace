import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { NewInvoiceComponent } from './new-invoice/new-invoice.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'invoice-list'
      },
      {
        path: 'invoice-list',
        component: InvoiceListComponent,
      },
      {
        path: 'new-invoice',
        component: NewInvoiceComponent
      },
      {
        path: 'edit-invoice/:id',
        component: NewInvoiceComponent,
        data: {
          title: 'Edit Invoice'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoiceRoutingModule { 



}
