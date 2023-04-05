import { Component, OnInit } from '@angular/core';
import { Book } from '../../model/book';
import { ActivatedRoute, Router } from '@angular/router';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { BooksService } from '../../services/books.service';

@Component({
  selector: 'bs-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.scss'],
})
export class BookEditComponent implements OnInit {
  book: Book | undefined;
  formEdit!: FormGroup;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private readonly booksService: BooksService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.book = this.activatedRoute.snapshot.data['book'];
    this.formEdit = this.fb.group({
      author: new FormControl(this.book!.author, {
        validators: [Validators.maxLength(50), Validators.required],
      }),
      description: new FormControl(this.book!.description, {
        validators: [Validators.maxLength(1000)],
      }),
      title: new FormControl(this.book!.title, {
        validators: [Validators.maxLength(50), Validators.required],
      }),
      year: new FormControl(this.book!.year, {
        validators: [
          Validators.min(1000),
          Validators.max(2023),
          Validators.required,
        ],
      }),
    });
  }

  onSubmit() {
    const bookToSave: Book = this.formEdit.value;
    this.booksService.updateBookById(this.book!.id, bookToSave).subscribe();
    this.router.navigate(['/']);
  }
}
