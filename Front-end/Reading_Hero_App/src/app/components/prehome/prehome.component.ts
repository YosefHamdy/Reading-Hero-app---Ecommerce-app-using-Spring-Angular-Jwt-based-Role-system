import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/services/auth/user-auth.service';

@Component({
  selector: 'app-prehome',
  templateUrl: './prehome.component.html',
  styleUrls: ['./prehome.component.css']
})
export class PrehomeComponent implements OnInit {

  constructor(private userAuthService: UserAuthService
              ,private router:Router) { }

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
