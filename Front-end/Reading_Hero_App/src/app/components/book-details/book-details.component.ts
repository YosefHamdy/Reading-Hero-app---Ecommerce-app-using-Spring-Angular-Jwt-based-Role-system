import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/common/book';
import { ShoppingItem } from 'src/app/common/shopping-item';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  // race condition where html template file is attempting to access property:
  // book.image_Url
  // but book is not assigned yet
  book: Book = new Book();
   
  constructor(private bookService:BookService 
    , private route:ActivatedRoute
    ,private cartService:CartService){}

  ngOnInit() {
    this.route.paramMap.subscribe(() =>
    {
      this.handleBookDetails();
    })
  }
  handleBookDetails() {
      // get book id param string converted to a number using + symbol
      const bookId: number  = Number(this.route.snapshot.paramMap.get('id'));
      
      this.bookService.getBook(bookId).subscribe(
        data => {
          this.book = data ;
        }
      )
  }
  addToCart(){

    const theCartItem = new ShoppingItem(this.book);
    this.cartService.addToCart(theCartItem);
  }

}
