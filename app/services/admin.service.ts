import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerClaim } from '../user-profile/customer-claim';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getPendingPolicy(): Observable<CustomerClaim[]>{

    var url = "http://localhost:8090/admin";

    return this.http.get<CustomerClaim[]>(url);

  }

  updatePolicy(claim: CustomerClaim){
    var url = "http://localhost:8090/admin/update";

    return this.http.post(url, claim);
  }

}
