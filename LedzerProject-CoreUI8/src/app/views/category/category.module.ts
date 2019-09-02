import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { CategoryRoutingModule } from './category-routing.module';
import { CatgoryListComponent } from './catgory-list/catgory-list.component';
import { CatgoryEditComponent } from './catgory-edit/catgory-edit.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [CatgoryListComponent, CatgoryEditComponent],
  imports: [
    CommonModule,
    CategoryRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class CategoryModule { }
