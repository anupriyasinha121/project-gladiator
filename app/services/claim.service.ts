import { Claim } from './../claim-insurance/claim';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  constructor(private http: HttpClient) { }

  claimPolicy(claim: Claim, userId:String): Observable<any>{
    console.log("claim Policy Called and the values are");
    console.log("Policy number " + claim.policyNumber);
    console.log("Claim Reason " + claim.claimReason);
    console.log("Mobile number " + claim.mobileNumber);

    var url = "http://localhost:8090/claim-policy/"+ userId;

    return this.http.post(url, claim);

  }
}
