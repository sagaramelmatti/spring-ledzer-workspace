import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PurchaseRoutingModule } from './purchase-routing.module';
import { PurchaseListComponent } from './purchase-list/purchase-list.component';
import { NewPurchaseComponent } from './new-purchase/new-purchase.component';

import { FormsModule , ReactiveFormsModule } from '@angular/forms';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './../../material/material.module';
import {MatDatepickerModule, MatInputModule,MatNativeDateModule,MatButtonModule, MatCardModule, MatFormFieldModule, MatCheckboxModule, MatRadioModule, MatSelectModule } from '@angular/material';
import { DialogOverviewExampleDialogComponent } from './../dialog-overview-example-dialog/dialog-overview-example-dialog.component';


@NgModule({
  declarations: [PurchaseListComponent, NewPurchaseComponent],
  imports: [
    CommonModule,
    PurchaseRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MaterialModule,
    MatDatepickerModule, 
    MatInputModule,
    MatNativeDateModule,
    MatInputModule, 
    MatButtonModule, 
    MatCardModule, 
    MatFormFieldModule, 
    MatCheckboxModule, 
    MatDatepickerModule, 
    MatRadioModule, 
    MatSelectModule
  ]
})
export class PurchaseModule { }
