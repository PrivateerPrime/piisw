import { Injectable } from '@angular/core';
import {
  Router,
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { BooksService } from '../services/books.service';
import { Review } from '../model/review';

@Injectable({
  providedIn: 'root',
})
export class BookReviewsResolver implements Resolve<Review[]> {
  constructor(private readonly booksService: BooksService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Review[]> {
    const id: number = +route.paramMap.get('bookId')!;
    return this.booksService.getReviewsByBookId(id);
  }
}
