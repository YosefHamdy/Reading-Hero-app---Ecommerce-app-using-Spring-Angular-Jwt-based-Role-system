import { Component, OnInit } from '@angular/core';
import { ShoppingItem } from 'src/app/common/shopping-item';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: ShoppingItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private cartService: CartService,
             private bookService:BookService) { }

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {

    // get a handle to the cart items
    this.cartItems = this.cartService.cartItems;

    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe( 
      data => this.totalQuantity = data
    );

    // compute cart total price and quantity
    this.cartService.assumeCartTotal();
  }


  incrementQuantity(theCartItem: ShoppingItem) {
    this.cartService.addToCart(theCartItem);
  }

  decrementQuantity(theCartItem: ShoppingItem) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: ShoppingItem) {
    this.cartService.remove(theCartItem);
  }
}
