import { Injectable } from '@angular/core';
import { Premium } from "../calculate-insurance/premium";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CalculateInsuranceService {

  constructor(private http: HttpClient) { }

   calculatePremium(prem:Premium):Observable<any>{
     console.log("Calculate Premium called and the values are");
     console.log("Type: "+prem.type);
     console.log("Age: "+prem.age);
     console.log("Engine Power: "+prem.enginePower);
     console.log("Model: "+prem.model);

     var url="http://localhost:8081/checkIns";

     return this.http.post('http://localhost:8090/calcIns',prem);
   }

}
