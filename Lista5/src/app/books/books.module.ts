import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookEditComponent } from './components/book-edit/book-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookReviewsComponent } from './components/book-details/book-reviews/book-reviews.component';

@NgModule({
  declarations: [BookListComponent, BookDetailsComponent, BookEditComponent, BookReviewsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class BooksModule {}
