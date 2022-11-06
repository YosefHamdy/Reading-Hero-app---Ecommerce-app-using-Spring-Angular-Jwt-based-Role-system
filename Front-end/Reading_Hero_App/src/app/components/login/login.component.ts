import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from 'src/app/services/auth/user-auth.service';
import { UserService } from 'src/app/services/auth/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService:UserService
            , private userAuthService:UserAuthService
            ,private router:Router) { }

  ngOnInit(): void { 
    
    if(this.userAuthService.isLoggedIn()){
      this.router.navigate(['/books']);
    }
  }

  login(loginForm:NgForm){
      this.userService.login(loginForm.value).subscribe(
        (response:any)=>{
           // console.log(response.jwtToken);
           // console.log(response.user.roleList);

            this.userAuthService.setRoles(response.user.roleList);
            this.userAuthService.setToken(response.jwtToken);
      //      console.log(response);
            const role = response.user.roleList[0];
            if(role.roleName === "ROLE_ADMIN"){
              this.router.navigate(['/panel']);
            }else {
              this.router.navigate(['/books']);

            }
            
        },
        (error) => {
          console.log(error);
        }
        );
  }

}
