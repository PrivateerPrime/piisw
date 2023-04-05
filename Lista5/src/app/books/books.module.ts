import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BookReviewsComponent } from './components/book-reviews/book-reviews.component';

@NgModule({
  declarations: [
    BookListComponent,
    BookReviewsComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ]
})
export class BooksModule { }
