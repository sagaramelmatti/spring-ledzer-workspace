import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductEditComponent } from './product-edit/product-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'product-list'
      },
      {
        path: 'product-list',
        component: ProductListComponent,
      },
      {
        path: 'product-add',
        component: ProductEditComponent
      },
      {
        path: 'product-edit/:id',
        component: ProductEditComponent,
        data: {
          title: 'Edit Product'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
