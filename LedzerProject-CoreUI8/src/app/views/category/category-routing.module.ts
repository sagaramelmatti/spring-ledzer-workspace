import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CatgoryListComponent } from './catgory-list/catgory-list.component';
import { CatgoryEditComponent } from './catgory-edit/catgory-edit.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Icons'
    },
    
    children: [
      {
        path: '',
        redirectTo: 'category-list'
      },
      {
        path: 'category-list',
        component: CatgoryListComponent,
      },
      {
        path: 'category-add',
        component: CatgoryEditComponent
      },
      {
        path: 'category-edit/:id',
        component: CatgoryEditComponent,
        data: {
          title: 'Edit Category'
        }
      },
      {
        path: 'sub-category-list',
        component: CatgoryListComponent,
      },
      {
        path: 'sub-category-add',
        component: CatgoryEditComponent
      },
      {
        path: 'sub-category-edit/:id',
        component: CatgoryEditComponent,
        data: {
          title: 'Edit Sub Category'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryRoutingModule { }
