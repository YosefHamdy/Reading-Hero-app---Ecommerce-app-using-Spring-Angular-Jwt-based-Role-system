import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Book } from '../common/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseUrl = 'http://localhost:8090/api/books'

  constructor(private httpClient: HttpClient) { }

  getBook(bookId: number): Observable<Book>{

    // build url "as in spring boot route controller "api/books/view/1" "
    const bookUrl = `${this.baseUrl}/view/${bookId}`;

    return this.httpClient.get<Book>(bookUrl);

  }

  getBookList(theCategoryId: number): Observable<Book[]>{

    // @TODO: need to build url based on category id .. 
    return this.httpClient.get<Book[]>(this.baseUrl).pipe(
      map(response => response)
    );
  }

  
  searchBooks(theKeyword: string): Observable<Book[]> {

      const searchUrl = `${this.baseUrl}/search/?keyword=${theKeyword}`;

      return this.getBooks(searchUrl);
  }


  private getBooks(searchUrl: string): Observable<Book[]> {
   
    return this.httpClient.get<Book[]>(searchUrl).pipe(
      map(response => response)
   
      );
  }
  addBook(book: FormData){
    const addUrl = `${this.baseUrl}/add`;
    return this.httpClient.post<any>(addUrl,book); 
  }

 deleteBook(isbn:number){
    return this.httpClient.delete("http://localhost:8090/api/books/delete/"+isbn);
 }

 updateBook(data:any,isbn : number){
    return this.httpClient.put<any>("http://localhost:8090/api/books/update/"+isbn, data);
 }
}

interface GetResponseBooks {
    books: Book[];
  }

