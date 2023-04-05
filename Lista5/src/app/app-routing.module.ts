import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './books/components/book-list/book-list.component';
import { BookListResolver } from './books/resolvers/book-list.resolver';
import { BookReviewsComponent } from './books/components/book-reviews/book-reviews.component';

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
  { path: 'books/:bookId/reviews', component: BookReviewsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
