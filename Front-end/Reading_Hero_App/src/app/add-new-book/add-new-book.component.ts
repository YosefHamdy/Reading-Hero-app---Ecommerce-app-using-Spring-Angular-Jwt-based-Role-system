import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Book } from '../common/book';
import { FileHandle } from '../common/file-handle.model';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {

  book : Book ={
    title: "",
    subTitle: "",
    subject: "",
    description: "",
    author_Name: "",
    image_url: "",
    price: 0,
    isbn: 0,
    bookImages: []
  }
  constructor(private bookService:BookService,
    private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
  }

  addBook(bookForm: NgForm){

   const bookFormData = this.prepareFormData(this.book);

     this.bookService.addBook(bookFormData).subscribe(
      (response: Book) => {
        bookForm.reset();
      },
      (error:HttpErrorResponse) => {
        console.log(error);
      }
     );
    console.log(this.book);
  }

  prepareFormData(book: Book): FormData{
    const formData = new FormData();

    formData.append(
      'book',
      // blob wouldn't send json directly
      // we are going to packet ir inside a blob 
      // then that blob send it to the backend
      new Blob([JSON.stringify(book)],{type:'contentType'})
    );

    for(let i = 0 ; i < book.bookImages.length;i++){
      formData.append(
        'imageFile',
        book.bookImages[i].file,
        book.bookImages[i].file.name
      );
    }
    return formData;
  }

  onFileSelected(event:any){
    if(event.target.files){
    const file = event.target.files[0];

    const fileHandle:FileHandle = {
      // this first file just akey from file model and second is the one we defined in 79 line
      file: file,
      // create url from file
      url: this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(file)
      )
    }

    this.book.bookImages.push(fileHandle);
    //this.book.image_url = (this.book.bookImages[0].url).toString();

    }
        
  }
}
