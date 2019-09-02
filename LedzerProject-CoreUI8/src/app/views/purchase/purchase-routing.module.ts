import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PurchaseListComponent } from './purchase-list/purchase-list.component';
import { NewPurchaseComponent } from './new-purchase/new-purchase.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'purchase-list'
      },
      {
        path: 'purchase-list',
        component: PurchaseListComponent,
      },
      {
        path: 'new-purchase',
        component: NewPurchaseComponent
      },
      {
        path: 'edit-purchase/:id',
        component: NewPurchaseComponent,
        data: {
          title: 'Edit Purchase'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PurchaseRoutingModule { }
