import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { TabsModule } from 'ngx-bootstrap/tabs';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { ToastrModule } from 'ngx-toastr';

import { AngularFontAwesomeModule } from 'angular-font-awesome';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};
import { AppComponent } from './app.component';
// Import containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { ProfileComponent } from './views/profile/profile.component';
import { HttpClientModule } from '@angular/common/http';

import { AppAuthModule } from './app-auth/app-auth.module';
import { AppUserModule } from './app-user/app-user.module';
import { AlertModule } from './views/alert/alert.module';
import { AppAlertModule } from './app-alert/app-alert.module';
import { MaterialModule } from './material/material.module';

import { AddDialogComponent } from './dialogs/add/add.dialog.component';
import { EditDialogComponent } from './dialogs/edit/edit.dialog.component';
import { DeleteDialogComponent } from './dialogs/delete/delete.dialog.component';
import { Page1Component } from './page1/page1.component';
import { DialogOverviewExampleDialogComponent } from './dialog-overview-example-dialog/dialog-overview-example-dialog.component';
import { NavbarComponent } from './navbar/navbar.component';



// Import bootstrap

const APP_CONTAINERS = [
  DefaultLayoutComponent
];

import {
  AppAsideModule,
  AppBreadcrumbModule,
  AppHeaderModule,
  AppFooterModule,
  AppSidebarModule,
} from '@coreui/angular';

// Import routing module
import { AppRoutingModule } from './app-routing.module';
// Import 3rd party components
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

/*
Source article for this code.
https://itnext.io/creating-forms-inside-modals-with-ng-bootstrap-221e4f1f5648
*/

@NgModule({
  declarations: [
    AppComponent,
    ...APP_CONTAINERS,
    P404Component,
    P500Component,
    LoginComponent,
    RegisterComponent,
    AddDialogComponent,
    EditDialogComponent,
    DeleteDialogComponent,
    Page1Component,
    ProfileComponent,
    DialogOverviewExampleDialogComponent,
    NavbarComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
		AppRoutingModule,
    AppAsideModule,
    AlertModule,
    AppAuthModule,
    AppUserModule,
    FormsModule, 
    ReactiveFormsModule,
		AppBreadcrumbModule.forRoot(),
		AppFooterModule,
		AppHeaderModule,
		AppSidebarModule,
    PerfectScrollbarModule,
    HttpClientModule,
    AppAlertModule,
    MaterialModule,
    AngularFontAwesomeModule,
		BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent],
  
  
})
export class AppModule { }
