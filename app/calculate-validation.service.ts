import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CalculateValidationService {

  constructor() { }
  notEmpty(string: String) : boolean{
    if(string.trim().length==0){
      return true;
    }
    return false;
  }


}
