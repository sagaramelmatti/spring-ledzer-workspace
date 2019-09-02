import { NgModule, LOCALE_ID  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { HttpClientModule } from '@angular/common/http';
import { InvoiceRoutingModule } from './invoice-routing.module';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { NewInvoiceComponent } from './new-invoice/new-invoice.component';
import { MaterialModule } from './../../material/material.module';
import {MatDatepickerModule, MatInputModule,MatNativeDateModule,MatButtonModule, MatCardModule, MatFormFieldModule, MatCheckboxModule, MatRadioModule } from '@angular/material';
import { DialogOverviewExampleDialogComponent } from './../dialog-overview-example-dialog/dialog-overview-example-dialog.component';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
  declarations: [InvoiceListComponent, NewInvoiceComponent,DialogOverviewExampleDialogComponent],
  imports: [
    CommonModule,
    InvoiceRoutingModule,
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
    MatSelectModule,
    ModalModule.forRoot(),
  ],
  providers: [{provide: LOCALE_ID, useValue: 'en-US'}],
})
export class InvoiceModule { }
