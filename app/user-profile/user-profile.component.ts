import { UserService } from './../services/user.service';
import { HttpClient } from '@angular/common/http';
import { CustomerClaim } from './customer-claim';
import { CustomerPolicy } from './customer-policy';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private router: Router, private userServive: UserService) { }

  loggedUserName:String="";
  loggedUser:String = "";
  policies:CustomerPolicy[] = [];
  claims:CustomerClaim[] = [];
  response;

  ngOnInit(): void {

    this.loggedUser = sessionStorage.getItem("customerId");    
    this.loggedUserName = sessionStorage.getItem("customerName");

    this.response = this.userServive.getCustomerPolicy(this.loggedUser).subscribe((customerPolicies)=>{
      this.policies = customerPolicies;
      // alert("Policies " + JSON.stringify(this.policies));
    })

    this.response = this.userServive.getCustomerClaim(this.loggedUser).subscribe((customerClaims)=>{
      this.claims = customerClaims;
      // alert("Claims "+JSON.stringify(this.claims));
    })
  }
}
