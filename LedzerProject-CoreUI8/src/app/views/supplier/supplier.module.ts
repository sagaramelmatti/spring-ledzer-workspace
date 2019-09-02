import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { TabsModule } from 'ngx-bootstrap/tabs';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';

import { HttpClientModule } from '@angular/common/http';
import { SupplierRoutingModule } from './supplier-routing.module';
import { SupplierListComponent } from './supplier-list/supplier-list.component';
import { SupplierEditComponent } from './supplier-edit/supplier-edit.component';

@NgModule({
  declarations: [SupplierListComponent, SupplierEditComponent],
  imports: [
    CommonModule,
    FormsModule,
	  ReactiveFormsModule,
    SupplierRoutingModule,
    HttpClientModule,
		ModalModule.forRoot(),
  ],
  providers: []
})
export class SupplierModule { }
