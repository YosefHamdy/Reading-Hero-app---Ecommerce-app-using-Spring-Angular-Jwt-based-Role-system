import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/services/auth/user-auth.service';
import { UserService } from 'src/app/services/auth/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  userService!:UserService;
  constructor(
    private userAuthService: UserAuthService
    ,private router:Router
    , userService: UserService
    ) {
      this.userService = userService;
     }

ngOnInit(): void {
}

public isLoggedIn(){
return this.userAuthService.isLoggedIn();
}

logout(){
this.userAuthService.clear();
this.router.navigate(['/login'])
}

}
