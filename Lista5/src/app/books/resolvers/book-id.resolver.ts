import { Injectable } from '@angular/core';
import {
  Router,
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { BooksService } from '../services/books.service';

@Injectable({
  providedIn: 'root',
})
export class BookIdResolver implements Resolve<Book> {
  constructor(private readonly booksService: BooksService) {}
  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Book> {
    const id: number = +route.paramMap.get('bookId')!;
    return this.booksService.getBookById(id);
  }
}
