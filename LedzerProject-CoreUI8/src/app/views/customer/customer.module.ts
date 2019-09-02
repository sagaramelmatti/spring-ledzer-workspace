import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { TabsModule } from 'ngx-bootstrap/tabs';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';

import { HttpClientModule } from '@angular/common/http';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerEditComponent } from './customer-edit/customer-edit.component';

@NgModule({
  declarations: [CustomerListComponent, CustomerEditComponent],
  imports: [
    CommonModule,
    FormsModule,
		ReactiveFormsModule,
    CustomerRoutingModule,
    HttpClientModule,
		ModalModule.forRoot(),
  ],
  providers: []
})
export class CustomerModule { }
