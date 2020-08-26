import { HttpClient } from '@angular/common/http';
import { Buy } from './../buy-insurance/buy';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyService {

  constructor(private http: HttpClient) { }

  buyPolicy(buy: Buy): Observable<any>{
    console.log("Vehicle Type " + buy.vehicleType);
    console.log("Manu " + buy.manufacturer);
    console.log("Model " + buy.model);
    console.log("Driving License " + buy.drivingLicense);
    console.log("PurchaseDate " + buy.purchaseDate);
    console.log("Reg Number " + buy.regNumber);
    console.log("Engine Number " + buy.engineNumber);
    console.log("Engine Power " + buy.enginePower);
    console.log("Chassis Nuumber " + buy.chassisNumber);
    console.log("Plan " + buy.plan);
    console.log("Plan Duration " + buy.planDuration);

    var url = "http://localhost:8090/buy-policy";

    return this.http.post(url, buy);

  }                                                                                                                                 
}
