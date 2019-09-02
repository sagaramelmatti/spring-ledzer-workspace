import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { AccountsRoutingModule } from './accounts-routing.module';
import { AccountgroupListComponent } from './accountgroup-list/accountgroup-list.component';
import { AccountgroupEditComponent } from './accountgroup-edit/accountgroup-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountEditComponent } from './account-edit/account-edit.component';

@NgModule({
  declarations: [AccountgroupListComponent, AccountgroupEditComponent, AccountListComponent, AccountEditComponent],
  imports: [
    CommonModule,
    AccountsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class AccountsModule { }
