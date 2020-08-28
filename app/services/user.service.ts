import { CustomerPolicy } from './../user-profile/customer-policy';
import { CustomerClaim } from './../user-profile/customer-claim';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCustomerClaim(userId:String):Observable<CustomerClaim[]>{

    var url = "http://localhost:8090/user-claim/" + userId;

    return this.http.get<CustomerClaim[]>(url);

  }


  getCustomerPolicy(userId:String):Observable<CustomerPolicy[]>{

    var url = "http://localhost:8090/user-policy/" + userId;

    return this.http.get<CustomerPolicy[]>(url);

  }

}
