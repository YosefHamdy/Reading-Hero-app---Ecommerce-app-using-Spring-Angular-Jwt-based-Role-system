import { ThisReceiver } from '@angular/compiler';
import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup,FormBuilder,Validators, NgForm } from '@angular/forms';
import { MatDialogRef ,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Book } from 'src/app/common/book';
import { FileHandle } from 'src/app/common/file-handle.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  bookForm !: FormGroup;
  actionBtn : string = "Save";
  //bookList !: Book[];
  constructor(private formBuilder : FormBuilder
    ,private bookService:BookService,
    // this editData have our row value
    @Inject(MAT_DIALOG_DATA) public editData : any,
    private dialogRef: MatDialogRef<DialogComponent>,
    private sanitizer: DomSanitizer,
    private router: Router) { 
      
    }
    get title(){
      return this.bookForm.get('title');
    }
    get subject(){
      return this.bookForm.get('subject');
    }
    get price(){
      return this.bookForm.get('price');
    }

  ngOnInit(): void {
    this.bookForm = this.formBuilder.group({
      title : ['',Validators.required],
      subject : ['',Validators.required],
      subTitle : [''],
      author_Name:[''],
      price : ['',Validators.required],
      description  :[''],
      image_url:['']
        });
    if(this.editData){
      this.actionBtn = "Update";
      this.bookForm.controls['title'].setValue(this.editData.title);
      this.bookForm.controls['subject'].setValue(this.editData.subject);
      this.bookForm.controls['price'].setValue(this.editData.price);
      this.bookForm.controls['author_Name'].setValue(this.editData.author_Name);
      this.bookForm.controls['subTitle'].setValue(this.editData.subTitle);
      this.bookForm.controls['description'].setValue(this.editData.description);
    }
    
  }
  
  addBook(){
    if(!this.editData){
    if(this.bookForm.valid){
      this.bookService.addBook(this.bookForm.value)
      .subscribe({
        next:(res)=>{
          alert("Book added successfully");
          this.bookForm.reset();
          this.dialogRef.close('Save');
          this.router.navigate(['/panel']);

        },
        error:()=>{
            alert("An error occured while trying to add this book");
        }
      });
    }
  }else{
      this.updateBook();
    }
  }
  updateBook(){
    this.bookService.updateBook(this.bookForm.value,this.editData.isbn)
    .subscribe({
      next:(res)=>{
        alert("Book Updated Successfully");
        this.bookForm.reset();
        this.dialogRef.close('Update');
      },
      error:() =>{
        alert("Error while updating record");
      }
    })
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

    this.bookForm.value.bookImages.push(fileHandle);

    }
        
  }
}
