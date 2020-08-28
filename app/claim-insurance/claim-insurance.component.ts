import { Component, OnInit, ɵConsole } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ValidationService } from '../validation.service';
import { ClaimService } from '../services/claim.service';
import { Claim } from "./claim";

@Component({
  selector: 'app-claim-insurance',
  templateUrl: './claim-insurance.component.html',
  styleUrls: ['./claim-insurance.component.css']
})

export class ClaimInsuranceComponent implements OnInit {

  validForm: ValidationService = new ValidationService();
  form: FormGroup;
  submitted: boolean;
  isLogged: number=0;

  constructor(private router: Router, private claimService: ClaimService) { }

  ngOnInit(): void {

    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
     if(this.isLogged<=0){
       this.router.navigateByUrl("/login"); 
     }

    this.form = new FormGroup({
      policyNumber: new FormControl('', Validators.required),
      claimReason: new FormControl('', Validators.required),
      mobileNumber: new FormControl('', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]),
    });

  }

  get f() { return this.form.controls; }
  
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
        return;
    }else if(this.validForm.notEmpty(this.form.controls['claimReason'].value)){ //this.validForm.notEmpty(this.form.controls['policyNumber'].value) || 
      alert("Some field are NULL");
      return;
    }else{

      var claim  = new Claim();
      claim.claimReason = this.form.controls['claimReason'].value;
      claim.mobileNumber = this.form.controls['mobileNumber'].value;
      claim.policyNumber = this.form.controls['policyNumber'].value;
      alert(this.form.controls['policyNumber'].value+ " " + this.form.controls['mobileNumber'].value + " " +this.form.controls['claimReason'].value);      
      var userId = sessionStorage.getItem("customerId");
      console.log("call server " + userId);
      let res = this.claimService.claimPolicy(claim, userId).subscribe(
        (claimNumber)=>{
          
            if(parseInt(claimNumber)<0){
              alert("Invalid Policy Number")
            }else{
              alert("Your Claim NUmber is" + claimNumber);
              this.router.navigate(['home'])
            }
         
        }
      )
      console.log(res);
    }

    // if(this.form.valid){
    //   this.router.navigateByUrl("/buy-policy");
    // }
  }
}