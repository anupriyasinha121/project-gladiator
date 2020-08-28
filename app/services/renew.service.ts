import { Renew } from './../renew-insurance/renew';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RenewService {

  constructor(private http: HttpClient) { }

  renewPolicy(renew : Renew, userId:String){

    console.log("" + renew.email);
    console.log("" + renew.policyNumber);
    console.log("" + renew.MobileNumber);
    console.log("" + renew.plan);
    console.log("" +renew.planDuration);

    var url = "http://localhost:8090/renew-policy/" + userId;

    return this.http.post(url, renew);

  }

}
