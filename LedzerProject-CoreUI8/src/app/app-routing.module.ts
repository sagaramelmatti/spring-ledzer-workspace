import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';

import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';

const routes: Routes = [
	
	{
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
	{
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'signup',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'base',
        loadChildren: './views/base/base.module#BaseModule'
      },
      {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'product',
        loadChildren: './views/product/product.module#ProductModule'
      },
      {
        path: 'invoice',
        loadChildren: './views/invoice/invoice.module#InvoiceModule'
      },
      {
        path: 'purchase',
        loadChildren: './views/purchase/purchase.module#PurchaseModule'
      },
      {
        path: 'accounts',
        loadChildren: './views/accounts/accounts.module#AccountsModule'
      },
      {
        path: 'category',
        loadChildren: './views/category/category.module#CategoryModule'
      },
      {
        path: 'subcategory',
        loadChildren: './views/category/category.module#CategoryModule'
      },
      {
        path: 'customer',
        loadChildren: './views/customer/customer.module#CustomerModule'
      },
	    {
        path: 'supplier',
        loadChildren: './views/supplier/supplier.module#SupplierModule'
      },
	    {
        path: 'taxes',
        loadChildren: './views/tax/tax.module#TaxModule'
      },
      {
        path: 'uom',
        loadChildren: './views/uom/uom.module#UomModule'
      },
      {
        path: 'invoicereport',
        loadChildren: './views/invoicereport/invoicereport.module#InvoicereportModule'
      },

      {
        path: 'purchasereport',
        loadChildren: './views/purchasereport/purchasereport.module#PurchasereportModule'
      },
      
      {
        path: 'auth',
        loadChildren: './views/auth/auth.module#AuthModule'
      }
      
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
