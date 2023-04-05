import { Component } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'bs-book-reviews',
  templateUrl: './book-reviews.component.html',
  styleUrls: ['./book-reviews.component.scss'],
})
export class BookReviewsComponent {
  readonly book: Book[];

  constructor(private readonly activatedRoute: ActivatedRoute) {
    this.book = this.activatedRoute.snapshot.data['book'];
  }
}
