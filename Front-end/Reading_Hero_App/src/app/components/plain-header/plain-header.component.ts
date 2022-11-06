import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/services/auth/user-auth.service';
import { UserService } from 'src/app/services/auth/user.service';

@Component({
  selector: 'app-plain-header',
  templateUrl: './plain-header.component.html',
  styleUrls: ['./plain-header.component.css']
})
export class PlainHeaderComponent implements OnInit {

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
