import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../../model/book';
import { BooksService } from '../../services/books.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'bs-book-reviews',
  templateUrl: './book-reviews.component.html',
  styleUrls: ['./book-reviews.component.scss'],
})
export class BookReviewsComponent implements OnInit {
  book: Book | undefined;

  constructor(
    private bookService: BooksService,
    private activeRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id: number;
    this.activeRoute.params.subscribe((params) => (id = +params['bookId']));
    this.bookService
      .getBookById(id!!)
      .subscribe((result) => (this.book = result));
  }
}
