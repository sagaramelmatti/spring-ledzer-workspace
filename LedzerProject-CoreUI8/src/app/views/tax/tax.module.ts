import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';

import { TabsModule } from 'ngx-bootstrap/tabs';
// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';

import { HttpClientModule } from '@angular/common/http';
import { TaxRoutingModule } from './tax-routing.module';
// Import bootstrap
import { TaxlistComponent } from './taxlist/taxlist.component';
import { TaxeditComponent } from './taxedit/taxedit.component';

// Import forms
// Import bootstrap
// Import forms
@NgModule({
  declarations: [
	TaxlistComponent,
	TaxeditComponent
  ],
  imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
		TaxRoutingModule,
		TabsModule,
		HttpClientModule,
		ModalModule.forRoot(),
  ],
	providers: [],
	entryComponents: [
  ]
  
})
export class TaxModule { }
