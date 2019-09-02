import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountgroupListComponent } from './accountgroup-list/accountgroup-list.component';
import { AccountgroupEditComponent } from './accountgroup-edit/accountgroup-edit.component';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountEditComponent } from './account-edit/account-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'accountgroup-list'
      },
      {
        path: 'accountgroup-list',
        component: AccountgroupListComponent,
      },
      {
        path: 'accountgroup-add',
        component: AccountgroupEditComponent
      },
      {
        path: 'accountgroup-edit/:id',
        component: AccountgroupEditComponent,
        data: {
          title: 'Edit Category'
        }
      },
      {
        path: 'account-list',
        component: AccountListComponent,
      },
      {
        path: 'account-add',
        component: AccountEditComponent
      },
      {
        path: 'account-edit/:id',
        component: AccountEditComponent,
        data: {
          title: 'Edit Category'
        }
      }
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountsRoutingModule { }
