import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ShoppingItem } from '../common/shopping-item';
import { ShoppingCartComponent } from '../components/shopping-cart/shopping-cart.component';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems : ShoppingItem[] = [];

  // subject is subclass of observable 
  // we use subject to publish events in our code
  // event will be sent to all subscribers
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();
  
  constructor() { }

  addToCart(theCartItem: ShoppingItem ){

    let alreadyExistsInCart:boolean = false ; 
    let existingCartItem: ShoppingItem = undefined!;

    if(this.cartItems.length > 0 ){

        existingCartItem = this.cartItems.find( temp =>
          temp.isbn === theCartItem.isbn)! ;

        alreadyExistsInCart = (existingCartItem != undefined)
    }// check if found

    if(alreadyExistsInCart){
      existingCartItem.quantity++;
    }else {
        // add the item to array
        this.cartItems.push(theCartItem);
    }
     
    // assume total price and total quantity of the cart

    this.assumeCartTotal();

  }
  assumeCartTotal() {
    let totalPrice : number = 0 ; 
    let totalQuantity: number = 0 ;

    for (let current of this.cartItems){
      totalPrice += current.quantity * current.unitPrice;
      totalQuantity += current.quantity;

    }

    // publish new values
    this.totalPrice.next(totalPrice);
    this.totalQuantity.next(totalQuantity);
  }


  decrementQuantity(theCartItem: ShoppingItem) {

    theCartItem.quantity--;

    if (theCartItem.quantity === 0) {
      this.remove(theCartItem);
    }
    else {
      this.assumeCartTotal();
    }
  }

  remove(theCartItem: ShoppingItem) {

    // get index of item in the array
    const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.isbn === theCartItem.isbn );

    // if found, remove the item from the array at the given index
    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);

      this.assumeCartTotal();
    }
  }



}
