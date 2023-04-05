import { TestBed } from '@angular/core/testing';

import { BookReviewsResolver } from './book-reviews.resolver';

describe('BookReviewsResolver', () => {
  let resolver: BookReviewsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(BookReviewsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
