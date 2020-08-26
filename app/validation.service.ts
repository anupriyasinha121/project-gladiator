import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  notEmpty(string: String) : boolean{
    if(string.trim().length<=2){
      return true;
    }
    return false;
  }

  validDate(date: Date): boolean{
    const today:Date = new Date();
    if(date.getTime()>today.getTime()){
      console.log("Invalid Date");
      return true;
    }

    return false;
  }
}