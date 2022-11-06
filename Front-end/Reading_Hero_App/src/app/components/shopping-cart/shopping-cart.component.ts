import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/common/book';
import { ShoppingItem } from 'src/app/common/shopping-item';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  constructor(private cartService : CartService,
              private route:ActivatedRoute) { }

  ngOnInit(): void {
  }

  addToCart(theBook: Book){
    
    const theCartItem = new ShoppingItem(theBook);

    this.cartService.addToCart(theCartItem);
  }
}
