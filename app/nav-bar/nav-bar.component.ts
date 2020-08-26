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

  ngOnInit(): void {
    // alert(sessionStorage.getItem("customerId"))
    // if(sessionStorage.getItem("customerId")!==null){
    //   alert("onInit "+ sessionStorage.getItem("customerId"));
    //   this.process="Logout";
    //   this.isUserLoggedIn = true;
    // }
  }

  onLogClick(){
    // if(sessionStorage.getItem("customerId")!=null){
    //   sessionStorage.setItem("customerId", null);
    //   sessionStorage.setItem("customerName", null);
    //   this.process="Login";
    //   this.isUserLoggedIn = false;
    //   this.router.navigateByUrl("/");
    // }else{
    //   this.router.navigateByUrl("/login");
    // }
  }

}