import { TestBed } from '@angular/core/testing';

import { BookIdResolver } from './book-id.resolver';

describe('BookIdResolver', () => {
  let resolver: BookIdResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(BookIdResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
