import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getManufacturers(type : number): Observable<String[]>{

    console.log("Service, type received " + type);

    var url = "http://localhost:8090/manufacturers";

    return this.http.post<String[]>(url, type);

  }

  getModels(manu: String, type:number): Observable<String[]>{

    console.log("Service, type received " + manu);

    var url = "http://localhost:8090/models";

    return this.http.post<String[]>(url, {"manufacturer":manu, "type":type});

  }

}
