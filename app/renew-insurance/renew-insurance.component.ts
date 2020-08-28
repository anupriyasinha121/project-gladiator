import { RenewService } from './../services/renew.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ValidationService } from '../validation.service';
import { Renew } from './../renew-insurance/renew';
import { Router } from '@angular/router';

@Component({
  selector: 'app-renew-insurance',
  templateUrl: './renew-insurance.component.html',
  styleUrls: ['./renew-insurance.component.css']
})
export class RenewInsuranceComponent implements OnInit {

  validForm: ValidationService = new ValidationService();
  
  form1 = new FormGroup({
    policyNumber: new FormControl('', Validators.required),
    mobileNumber: new FormControl('', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
    email: new FormControl('', [Validators.required])
  });
  
  form2 = new FormGroup({
    planYear: new FormControl('', Validators.required)
  });
  form2Submitted: boolean;
  showForm2: boolean;
  plan: string;
  form1Submitted: boolean;
  form1Completed: boolean;
  renewDetail:Renew = new Renew();
  isLogged:number=0;

  constructor(private router: Router, private renewService : RenewService) { }

  ngOnInit(): void {

    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
    if(this.isLogged<=0){
      this.router.navigateByUrl("/login"); 
    }

  }

  get f1() { 
    return this.form1.controls; 
  }

  onSubmit1(){
    this.form1Submitted = true;

    // stop here if form is invalid
    if (this.form1.invalid) {
        return;
    }else{
      this.form1Completed = true;
      
      this.renewDetail.policyNumber = this.form1.controls['policyNumber'].value;
      this.renewDetail.email = this.form1.controls['email'].value;
      this.renewDetail.MobileNumber = this.form1.controls['mobileNumber'].value;
    }

  }

  onTPLClick(){
    this.showForm2 = true;
    this.plan = "TPL";
  }

  onComprehensiveClick(){
    this.showForm2 = true;
    this.plan = "Comp";
  }

  get f2() { 
    return this.form2.controls; 
  }

  onSubmit2() {
    this.form2Submitted = true;

    // stop here if form is invalid
    if (this.form2.invalid) {
        return;
    }else{
      this.renewDetail.plan = this.plan;
      this.renewDetail.planDuration = this.form2.controls['planYear'].value;
      var userId = sessionStorage.getItem("customerId");
      console.log("call server " + userId);
      var response = this.renewService.renewPolicy(this.renewDetail, userId).subscribe((policyNumber)=>{
          alert("Policy Id : " + policyNumber);
          this.router.navigate(['user-profile']);
      })
    }
  }

}
