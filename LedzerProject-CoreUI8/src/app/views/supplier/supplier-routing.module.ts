import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SupplierListComponent } from './supplier-list/supplier-list.component';
import { SupplierEditComponent } from './supplier-edit/supplier-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'supplier-list'
      },
      {
        path: 'supplier-list',
        component: SupplierListComponent,
      },
      {
        path: 'supplier-add',
        component: SupplierEditComponent
      },
      {
        path: 'supplier-edit/:id',
        component: SupplierEditComponent,
        data: {
          title: 'Edit Supplier'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SupplierRoutingModule { }
