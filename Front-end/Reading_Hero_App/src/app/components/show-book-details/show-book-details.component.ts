import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit,AfterViewInit,ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Book } from 'src/app/common/book';
import { BookService } from 'src/app/services/book.service';
import { DialogComponent } from '../dialog/dialog.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';



@Component({
  selector: 'app-show-book-details',
  templateUrl: './show-book-details.component.html',
  styleUrls: ['./show-book-details.component.css']
})
export class ShowBookDetailsComponent implements OnInit {

  currentCategoryId!: number;
  bookDetails: Book[] = [];
  displayedColumns: string[] = ['Title', 'Subtitle', 'Subject', 'Author','Edit'];
 // dataSource = new Book;

  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private bookService:BookService
    ,private router: Router,
    public dialog:MatDialog) { }

  ngOnInit(): void {
    this.getAllBooks();
  }
  openDialog(): void {
    this.dialog.open(DialogComponent, {
      width: '30%',
      
    }).afterClosed().subscribe(val =>{
      if(val === 'Save'){
        this.getAllBooks();
      }
    })
  }
  editBook(row:any){
    this.dialog.open(DialogComponent,{
      width:'30%',
      data:row
    }).afterClosed().subscribe(val =>{
      if(val==='Update'){
        this.getAllBooks();
      }
    })
    
  }

 getAllBooks(){
    this.bookService.getBookList(this.currentCategoryId).subscribe(
      (response: Book[]) =>{
        //this.bookDetails = response;
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },(error:HttpErrorResponse)=>{
        console.log(error);
      }
    );
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteBook(isbn: any){
    if(confirm("Are you sure to delete this book !")) {

      this.bookService.deleteBook(isbn).subscribe(
        (response: any) => {
          this.getAllBooks();
        },
        (error:HttpErrorResponse) =>{
        console.log(error);
        this.getAllBooks();

      }
        );
    }
  }


}
