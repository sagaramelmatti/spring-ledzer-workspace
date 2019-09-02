import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'purchase-history'
      },
      {
        path: 'purchase-history',
        component: PurchaseHistoryComponent,
      }
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PurchasereportRoutingModule { }
