import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  BaseUrl = "http://localhost:8090/api/v1/auth";
  // this header indicate that there is no authentication required to access the login
  // (permitAll)
  requestHeader = new HttpHeaders({"No-Auth":"True"});
  // inject http
  constructor(private httpclient: HttpClient,
              private userAuthService:UserAuthService) { }

  login(loginData: any){
    // here post function always try to find requestHeader so we path it
    return this.httpclient.post(this.BaseUrl, loginData, 
    {headers: this.requestHeader});
  }

  roleMatch(allowedRoles:any) :boolean  { 
    let isMatch = false ;
    const userRoles: any =  this.userAuthService.getRoles();

    if(userRoles != null && userRoles){
      for(let i = 0 ; i < userRoles.length ; i++){
        for(let j = 0; j < allowedRoles.length; j++){
          if(userRoles[i].roleName === allowedRoles[j]){
          isMatch = true;
          return isMatch;
          }else if(userRoles == null || undefined) {
            return false;
          }
        }
      }
    }
    return isMatch;
  }
}
