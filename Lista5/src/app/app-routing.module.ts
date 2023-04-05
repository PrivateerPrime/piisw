import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './books/components/book-list/book-list.component';
import { BookListResolver } from './books/resolvers/book-list.resolver';
import { BookReviewsComponent } from './books/components/book-reviews/book-reviews.component';
import { BookIdResolver } from './books/resolvers/book-id.resolver';
import { BookEditComponent } from './books/components/book-edit/book-edit.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/books',
  },
  {
    path: 'books',
    component: BookListComponent,
    resolve: {
      books: BookListResolver,
    },
  },
  {
    path: 'books/:bookId/reviews',
    component: BookReviewsComponent,
    resolve: {
      book: BookIdResolver,
    },
  },
  {
    path: 'books/:bookId/edit',
    component: BookEditComponent,
    resolve: {
      book: BookIdResolver,
    },
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
