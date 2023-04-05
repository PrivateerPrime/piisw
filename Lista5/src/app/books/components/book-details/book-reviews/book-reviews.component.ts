import { Component, Input, OnInit } from '@angular/core';
import { Review } from '../../../model/review';

@Component({
  selector: 'bs-book-reviews',
  templateUrl: './book-reviews.component.html',
  styleUrls: ['./book-reviews.component.scss'],
})
export class BookReviewsComponent implements OnInit {
  @Input()
  review!: Review;
  constructor() {}

  ngOnInit(): void {}
}
