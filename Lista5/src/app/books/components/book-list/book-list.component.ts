import { Component, OnInit, ViewChild } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { BooksService } from '../../services/books.service';

@Component({
  selector: 'bs-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
})
export class BookListComponent implements OnInit {
  @ViewChild('bookSearch')
  searchBar!: HTMLInputElement;
  readonly books: Book[];
  private $observeBooks: Observable<Book[]> | undefined;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly bookService: BooksService
  ) {
    this.books = this.activatedRoute.snapshot.data['books'];
  }

  ngOnInit(): void {
    // this.searchBar;
    this.$observeBooks = this.bookService.getBooksByName('Lem');
  }

  onValueChanges(searchText: string) {
    // console.log($event);
  }
}
