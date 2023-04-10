import { Component, Input, OnInit } from '@angular/core';
import { Review } from '../../../model/review';

@Component({
  selector: 'bs-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.scss'],
})
export class ReviewListComponent {
  @Input()
  review!: Review;
  constructor() {}
}
