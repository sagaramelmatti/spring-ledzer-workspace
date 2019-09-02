import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerEditComponent } from './customer-edit/customer-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'customer-list'
      },
      {
        path: 'customer-list',
        component: CustomerListComponent,
      },
      {
        path: 'customer-add',
        component: CustomerEditComponent
      },
      {
        path: 'customer-edit/:id',
        component: CustomerEditComponent,
        data: {
          title: 'Edit Customer'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
