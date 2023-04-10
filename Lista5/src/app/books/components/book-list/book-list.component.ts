import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute } from '@angular/router';
import { BooksService } from '../../services/books.service';
import {
  debounceTime,
  distinctUntilChanged,
  iif,
  Subject,
  switchMap,
} from 'rxjs';

@Component({
  selector: 'bs-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
})
export class BookListComponent implements OnInit {
  searchTerms = new Subject<string>();
  books: Book[] | undefined;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly bookService: BooksService
  ) {}

  ngOnInit(): void {
    this.books = this.activatedRoute.snapshot.data['books'];

    this.searchTerms
      .pipe(
        debounceTime(200),
        distinctUntilChanged(),
        switchMap((value) =>
          iif(
            () => value.length > 1,
            this.bookService.getBooksByName(value),
            this.bookService.getAllBooks()
          )
        )
      )
      .subscribe((response) => {
        this.books = response;
      });
  }

  onValueChanges(searchText: string) {
    this.searchTerms.next(searchText);
  }
}
