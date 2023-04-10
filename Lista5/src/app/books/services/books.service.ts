import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { Review } from '../model/review';

const booksApiPrefix = '/api/books';
const reviewsApiPrefix = '/api/reviews';

@Injectable({
  providedIn: 'root',
})
export class BooksService {
  constructor(private readonly http: HttpClient) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(booksApiPrefix);
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${booksApiPrefix}/${id}`);
  }

  updateBookById(id: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${booksApiPrefix}/${id}`, book);
  }

  getReviewsByBookId(id: number) {
    return this.http.get<Review[]>(`${reviewsApiPrefix}?forBook=${id}`);
  }

  createReviewForBook(review: Review) {
    return this.http.post<Review>(reviewsApiPrefix, review);
  }

  getBooksByName(name: string) {
    return this.http.get<Book[]>(`${booksApiPrefix}?q=${name}`);
  }
}
