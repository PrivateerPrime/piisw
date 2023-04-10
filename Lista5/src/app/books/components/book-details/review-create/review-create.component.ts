import { Component, Input, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { BooksService } from '../../../services/books.service';
import { Review } from '../../../model/review';
import { Router } from '@angular/router';

@Component({
  selector: 'bs-review-create',
  templateUrl: './review-create.component.html',
  styleUrls: ['./review-create.component.scss'],
})
export class ReviewCreateComponent implements OnInit {
  @Input()
  bookId: number | undefined;
  formReview!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private readonly booksService: BooksService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.formReview = this.fb.group({
      title: new FormControl(null, { validators: [Validators.required] }),
      description: new FormControl(null, { validators: [Validators.required] }),
      rate: new FormControl(null, {
        validators: [Validators.required, Validators.min(1), Validators.max(5)],
      }),
    });
  }

  onSubmit() {
    const review: Review = this.formReview.value;
    review.forBook = this.bookId!;
    this.booksService.createReviewForBook(review).subscribe();
    this.router.navigate(['/']);
  }
}
