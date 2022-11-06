import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  setRoles(roles:[]){
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): []{
    
    return JSON.parse(localStorage.getItem('roles') || '{}');
  }

  setToken(jwtToken:string){
    localStorage.setItem("jwtToken",jwtToken);
  }
  
  getToken(): string | any{
  return localStorage.getItem('jwtToken');
  }
  isLoggedIn(){
    return this.getRoles() && this.getToken();
  }
 
  clear(){
    localStorage.clear();
  }
}
