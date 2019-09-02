import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { UomRoutingModule } from './uom-routing.module';
import { UomListComponent } from './uom-list/uom-list.component';
import { UomEditComponent } from './uom-edit/uom-edit.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [UomListComponent, UomEditComponent],
  imports: [
    CommonModule,
    UomRoutingModule,
		FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: []
	
})
export class UomModule { }
