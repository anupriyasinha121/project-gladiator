import { User } from './login/user';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Login } from './login/login';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class LoginService {
    
  //  baseUrl = "http://localhost:8080/login";

    constructor(private http: HttpClient) { }

         
    userLogin(login : Login ): Observable<User>{
      //console.log("Successful login");
      console.log("email " + login.email);
      console.log("password " + login.password);
      return this.http.post<User>("http://localhost:8090/login", login);  //{responseType:'text'}
    }

    adminLogin(login: Login): Observable<User>{
      console.log("email " + login.email);
      console.log("password " + login.password);
      return this.http.post<User>("http://localhost:8090/admin-login", login);
    }
}