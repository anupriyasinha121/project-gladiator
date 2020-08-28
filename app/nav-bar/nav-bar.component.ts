import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private router: Router) { }

  process:String = "Login";
  isUserLoggedIn:boolean = false;
  currentUser: String;
  isLogged:number = 0;

  ngOnInit(): void {
    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
    // alert("onInit "+sessionStorage.getItem("customerId"))
    if(this.isLogged>0){
      // alert("onInit inside if "+ sessionStorage.getItem("customerId"));
      this.process="Logout";
      this.isUserLoggedIn = true;
    }
  }

  onLogClick(){
    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
    
    if(this.isLogged>0){
      sessionStorage.setItem("customerId", "");
      sessionStorage.setItem("customerName", "");
      sessionStorage.setItem("isLogged", "0");
      this.process="Login";
      this.isUserLoggedIn = false;
      this.router.navigateByUrl("/");
    }else{
      this.router.navigateByUrl("/login");
    }
  }

}