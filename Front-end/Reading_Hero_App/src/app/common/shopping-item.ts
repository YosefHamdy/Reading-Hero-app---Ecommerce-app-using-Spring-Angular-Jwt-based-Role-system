import { Book } from "./book";

export class ShoppingItem {

    isbn: number;
    title: string; 
    imageUrl:string;
    unitPrice:number;

    quantity: number;

    constructor(book:Book){
        this.isbn = book.isbn ; 
        this.title = book.title ;
        this.imageUrl = book.image_url;
        this.unitPrice = book.price;

        this.quantity =  1;


    }

}
