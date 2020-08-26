import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder,Validators } from '@angular/forms';
import { CalculateValidationService  } from '../calculate-validation.service';
import { Premium } from "./premium";
import { CalculateInsuranceService } from "../services/calculate-insurance.service";
import { Router } from "@angular/router";
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { ResultPremium } from "./resultPremium";

@Component({
  selector: 'app-calculate-insurance',
  templateUrl: './calculate-insurance.component.html',
  styleUrls: ['./calculate-insurance.component.css']
})
export class CalculateInsuranceComponent implements OnInit {

  show:boolean=false;
  submitted = false;
  form1: FormGroup;
  loading:any;;
  form1Completed :boolean=false;
  validForm: CalculateValidationService = new CalculateValidationService();
  vehicleType: number = 0;
  tpl:any=null;
  comp:any=null;
  
  
  constructor(private router: Router,private formBuilder: FormBuilder,private calcService:CalculateInsuranceService) { }

  ngOnInit(){
    this.form1 = this.formBuilder.group({
      Age: ['', Validators.required],
      Model: ['', Validators.required],
      EnginePower:['',Validators.required]
      
  });
  }
  get f1() { return this.form1.controls; }
  
  onVehicle2Click(){
    this.vehicleType = 2;
    this.form1Completed = true;

  }
  
  onVehicle4Click(){
    this.vehicleType = 4;
    this.form1Completed = true;
  }
  

  onForm1Submit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.form1.invalid) {
        return;
    }
    else if(this.validForm.notEmpty(this.form1.controls['Age'].value) || this.validForm.notEmpty(this.form1.controls['Model'].value)
    || this.validForm.notEmpty(this.form1.controls['EnginePower'].value)){
      // console.log(this.form.controls['claimReason'].value);
      alert("Some field are NULL");
      return;
    }else{
        var prem=new Premium();
        prem.age=this.form1.controls['Age'].value;
        prem.enginePower=this.form1.controls['EnginePower'].value;
        prem.type=this.vehicleType;
        prem.model=this.form1.controls['Model'].value;

    

        var resultP=new ResultPremium();

        let res = this.calcService.calculatePremium(prem).subscribe(user=>{
          // if(user.status=='SUCCESS')
          // {
            // let userName= user.userNameFirst;
            // let userId= user.userId;
  
            // sessionStorage.setItem('userName', userName)
            // sessionStorage.setItem('userId', userId)
            // resultP.resultTpl=sessionStorage.getItem('resultTpl');
            // resultP.resultComp=sessionStorage.getItem('resultComp');

            resultP=user;
            this.router.navigate(['calculate-premium']);
            this.tpl=resultP.resultTpl;
            this.comp=resultP.resultComp;
            // console.log("TPL: "+resultP.resultTpl);
            // console.log("Comp: "+resultP.resultComp);
          // }
          // else
          // {
          //   alert(user.status)
            // this.message=user.message
            // this.router.navigate(['user'])
          // }
        }
      )


      this.show=true;

      console.log(res);
    }

    }
  }
  


