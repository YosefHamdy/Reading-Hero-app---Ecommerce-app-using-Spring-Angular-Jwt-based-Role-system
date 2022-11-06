import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError, Observable, throwError } from "rxjs";
import { UserAuthService } from "../services/auth/user-auth.service";

@Injectable({
    providedIn: 'root'
  })
export class AuthInterceptor implements HttpInterceptor{
  
    constructor(private userAuthService: UserAuthService
        ,private router:Router){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        if (req.headers.get('No-Auth') === 'True'){
            return next.handle(req.clone());
        }
        const token = this.userAuthService.getToken();
        req = this.addToken(req, token);

        return next.handle(req).pipe(
            catchError(
                (err:HttpErrorResponse) => {
                    console.log(err.status);
                    if(err.status === 401){
                        //401 is not logged in 
                        //so navigate to login
                        this.router.navigate(['/login']);
                    }else if(err.status === 403){
                        // if not authorized navigate to forbidden page
                        this.router.navigate(['/forbidden']);
                    }
                    return throwError("Something gone wrong !");
                }
            )
        )

            
    }

    private addToken(request:HttpRequest<any>,token:string){
        return request.clone({
            setHeaders:{
                Authorization :`Bearer ${token}`
            }
        });
    }
    
}