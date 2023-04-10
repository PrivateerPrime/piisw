import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookListComponent } from './components/book-list/book-list.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { BookEditComponent } from './components/book-edit/book-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReviewListComponent } from './components/book-details/review-list/review-list.component';
import { ReviewCreateComponent } from './components/book-details/review-create/review-create.component';

@NgModule({
  declarations: [
    BookListComponent,
    BookDetailsComponent,
    BookEditComponent,
    ReviewListComponent,
    ReviewCreateComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class BooksModule {}
