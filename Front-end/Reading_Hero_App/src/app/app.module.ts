import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BookListComponent } from './components/book-list/book-list.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http'
import { BookService } from './services/book.service';
import { RouterModule, Routes } from '@angular/router';
import { BookDetailsComponent } from './components/book-details/book-details.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './components/admin/admin.component';
import { LoginComponent } from './components/login/login.component';
import { ForbiddenComponent } from './components/forbidden/forbidden.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PrehomeComponent } from './components/prehome/prehome.component';
import { HeaderComponent } from './components/header/header.component';
import { SearchComponent } from './components/search/search.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './services/auth/user.service';
import { AddNewBookComponent } from './add-new-book/add-new-book.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { ShowBookDetailsComponent } from './components/show-book-details/show-book-details.component';
import { PlainHeaderComponent } from './components/plain-header/plain-header.component';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogComponent } from './components/dialog/dialog.component';
import {MatSelectModule} from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import {MatGridListModule} from '@angular/material/grid-list';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';






 
const routes: Routes= [
  {path: 'books/:id', component: BookDetailsComponent},
  {path: 'search/:keyword', component: BookListComponent},
  {path: 'cart-details', component: CartDetailsComponent},
  {path: 'checkout', component: CheckoutComponent},
  {path: 'panel', component: ShowBookDetailsComponent,canActivate:[AuthGuard], data:{roleList:['ROLE_ADMIN']}}, 
  {path: 'home', component: PrehomeComponent}, 
  {path: 'books', component: BookListComponent}, 
  {path: 'admin', component: AdminComponent, canActivate:[AuthGuard], data:{roleList:['ROLE_ADMIN']}},
  {path: 'user', component: UserComponent, canActivate:[AuthGuard], data:{roleList:['ROLE_USER']}},
  {path: 'login', component: LoginComponent},
  {path: 'addNewBook', component: AddNewBookComponent,canActivate:[AuthGuard], data:{roleList:['ROLE_ADMIN']}},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: '', redirectTo: '/books', pathMatch: 'full'},
  {path: '**', redirectTo: '/books', pathMatch: 'full'}
]
@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookDetailsComponent,
    UserComponent,
    AdminComponent,
    LoginComponent,
    ForbiddenComponent,
    HomeComponent,
    PrehomeComponent,
    HeaderComponent,
    SearchComponent,
    SidebarComponent,
    AddNewBookComponent,
    ShowBookDetailsComponent,
    PlainHeaderComponent,
    DialogComponent,
    ShoppingCartComponent,
    CartStatusComponent,
    CartDetailsComponent,
    CheckoutComponent
  ],
  imports: [ 
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule,
    MatSelectModule,
    MatSortModule,
    MatGridListModule,
    ReactiveFormsModule
  ],
  exports:[
    SearchComponent
  ],
  providers: [
    BookService,
    
    AuthGuard,{
        provide: HTTP_INTERCEPTORS,
        useClass:AuthInterceptor,
        multi:true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
