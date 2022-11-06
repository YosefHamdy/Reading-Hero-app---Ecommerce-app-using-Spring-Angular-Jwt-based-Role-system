import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map, VirtualTimeScheduler } from 'rxjs';
import { Book } from 'src/app/common/book';
import { ShoppingItem } from 'src/app/common/shopping-item';
import { ImageProcessingService } from 'src/app/image-processing.service';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list-grid.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  

  books!: Book[];
  currentCategoryId!: number;
  searchMode !: boolean;

  constructor(private bookListService:BookService,
              private route: ActivatedRoute,
              private cartService : CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listBook(); 
    });
   
  }
  listBook(){
      // check that if this route has parameter for keyword
      // because if it has parameter we performing search
      // the keyword string came from the route we configured in app module\
      // and the value came from search component
      this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if (this.searchMode){
      this.handleSearchBooks();
    }else{
      this.handleListBooks();
    }
  }
  handleSearchBooks(){

    const theKeyword: any = this.route.snapshot.paramMap.get('keyword') ;
    // now search for the books using keyword
    this.bookListService.searchBooks(theKeyword).subscribe(
      data => { 
        this.books = data ;
      }
    );
  
  }
  handleListBooks() {
        // check if 'id' parameter is available 
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if(hasCategoryId){
      //  get the "id" param string. convert string to a number useing "+" symbol
      const param=this.route.snapshot.paramMap.get('id');
      this.currentCategoryId= param?+param:0;

    }else {
      // not category id available .. default to category 1 \
      this.currentCategoryId = 1;
    }
    // now get the books for the given category id
    this.bookListService.getBookList(this.currentCategoryId)
   // .pipe(
   //   map((x: Book[], i) => x.map((book: Book) => this.imageService.convertShowImages(book)))
   // )
    .subscribe(
      data => {
        this.books = data;
      }
    )
  }
  addToCart(theBook: Book){
    
    const theCartItem = new ShoppingItem(theBook);

    this.cartService.addToCart(theCartItem);
  }
}
