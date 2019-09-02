import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaxlistComponent } from './taxlist/taxlist.component';
import { TaxeditComponent } from './taxedit/taxedit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'tax-list'
      },
      {
        path: 'tax-list',
        component: TaxlistComponent,
      },
      {
        path: 'tax-add',
        component: TaxeditComponent
      },
      {
        path: 'tax-edit/:id',
        component: TaxeditComponent,
        data: {
          title: 'Edit Tax'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TaxRoutingModule { }
