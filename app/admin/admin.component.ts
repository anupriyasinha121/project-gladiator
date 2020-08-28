import { AdminService } from './../services/admin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerClaim } from '../user-profile/customer-claim';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private router: Router, private adminService: AdminService) { }

  currentUser:String = "";
  pendingClaims:CustomerClaim[] = [];
  status:String = "";
  amount:number = 0;

  ngOnInit(): void {
      this.currentUser = sessionStorage.getItem("customerId");
      if(this.currentUser!="admin"){
        this.router.navigate(['admin-login']);
      }

      var res =  this.adminService.getPendingPolicy().subscribe((data)=>{ 
        console.log("Models from server " + JSON.stringify(data));
        this.pendingClaims = data;
      })
  }
  
  updateClaim(claim: CustomerClaim){
    var updatedClaim = new CustomerClaim();

    updatedClaim.claimId = claim.claimId;
    updatedClaim.claimDate = claim.claimDate;
    updatedClaim.claimAmount = this.amount;
    updatedClaim.claimReason = claim.claimReason;
    updatedClaim.policyId = claim.policyId;

    let res = this.adminService.updatePolicy(updatedClaim).subscribe((data)=>{

    });

    res =  this.adminService.getPendingPolicy().subscribe((data)=>{
      console.log("Models from server " + JSON.stringify(data));
      this.pendingClaims = data;
    });
  }
}
