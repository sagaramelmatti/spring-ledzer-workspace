import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { TabsModule } from 'ngx-bootstrap/tabs';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';

import { HttpClientModule } from '@angular/common/http';
import { ProductRoutingModule } from './product-routing.module';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductEditComponent } from './product-edit/product-edit.component';

@NgModule({
  declarations: [ProductListComponent, ProductEditComponent],
  imports: [
    CommonModule,
    FormsModule,
		ReactiveFormsModule,
    ProductRoutingModule,
    HttpClientModule,
		ModalModule.forRoot()
  ]
})
export class ProductModule { }
