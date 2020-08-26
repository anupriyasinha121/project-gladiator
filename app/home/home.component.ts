import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) { }

  loggedUserId:String = null;

  ngOnInit(): void {
    this.loggedUserId = sessionStorage.getItem("customerId");
  }

  onBuyClick(){
    if(this.loggedUserId==null){
      this.router.navigateByUrl("/login");     //
    }else{
      this.router.navigateByUrl("/buy-policy");
    }
  }

  onRenewClick(){
    if(this.loggedUserId==null){
      this.router.navigateByUrl("/login");
    }else{
      this.router.navigateByUrl("/renew-policy");
    }
  }

  onClaimClick(){
    if(this.loggedUserId==null){
      this.router.navigateByUrl("/login");
    }else{
      this.router.navigateByUrl("/claim-policy");
    }
  }

  onCalculateClick(){
    this.router.navigateByUrl("/calculate-premium");
  }

}
