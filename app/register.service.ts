import {Customerregister} from './register/customerregister';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }
    
      
      
  registration(customerregister: Customerregister): Observable<any>{
    console.log("registration Called and the values are");
    console.log("email " + customerregister.email);
    console.log("name " + customerregister.name);
    console.log("password" + customerregister.password);
    console.log("address" + customerregister.address);
    console.log("dob" + customerregister.dob);
    console.log("contact" + customerregister.contact);

    var url = "http://localhost:8090/register";

    return this.http.post(url, customerregister);
        }
      }