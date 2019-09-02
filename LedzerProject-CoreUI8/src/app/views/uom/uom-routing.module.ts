import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UomListComponent } from './uom-list/uom-list.component';
import { UomEditComponent } from './uom-edit/uom-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'uom-list'
      },
      {
        path: 'uom-list',
        component: UomListComponent,
      },
      {
        path: 'uom-add',
        component: UomEditComponent
      },
      {
        path: 'uom-edit/:id',
        component: UomEditComponent,
        data: {
          title: 'Edit Uom'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UomRoutingModule { }
