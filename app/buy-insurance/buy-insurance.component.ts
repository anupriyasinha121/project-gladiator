import { VehicleService } from './../services/vehicle.service';
import { BuyService } from './../services/buy.service';
import { ValidationService } from './../validation.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Buy } from './buy';
import { Premium } from "../calculate-insurance/premium";
import { ResultPremium } from "../calculate-insurance/resultPremium";
import { CalculateInsuranceService } from "../services/calculate-insurance.service";

@Component({
  selector: 'app-buy-insurance',
  templateUrl: './buy-insurance.component.html',
  styleUrls: ['./buy-insurance.component.css']
})

export class BuyInsuranceComponent implements OnInit {

   validForm: ValidationService = new ValidationService();

  form1 = new FormGroup({
    manuf: new FormControl('', [Validators.required]),
    model: new FormControl('', Validators.required),
    drivingLicence: new FormControl('', Validators.required),
    purchaseDate: new FormControl('', [Validators.required]),
    registrationNumber: new FormControl('', Validators.required),
    engineNumber: new FormControl('', Validators.required),
    enginePower: new FormControl('', [Validators.required, Validators.min(1), Validators.max(6000)]),
    chassisNumber: new FormControl('', Validators.required),
  });

  form2 = new FormGroup({
    plantype: new FormControl('', Validators.min(1)),
    planYear: new FormControl('', Validators.required)
  });

  showForm1 : boolean = false;
  vehicleType: number = 0;
  showForm2:boolean = false;
  plan:String = "";
  amount:number;
  form2Submitted: boolean = false;
  form1Submitted: boolean = false;
  form1Completed:boolean = false;
  tpl:any=null;
  comp:any=null;
  buy:Buy = new Buy();

  manufacturers: String[] =["Honda","Tata"]
  models: String[] =["city","Indigo"]
  isLogged:number=0;

  constructor(private router: Router, private buyService: BuyService, private vehicleService: VehicleService,private calcService:CalculateInsuranceService) { }

  ngOnInit(): void {
    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
     if(this.isLogged<=0){
       this.router.navigateByUrl("/login"); 
     }
  }

  onVehicle2Click(){
    this.vehicleType = 2;
    this.showForm1 = true;
    this.getVehicleManuf();
  }

  onVehicle4Click(){
    this.vehicleType = 4;
    this.showForm1 = true;
    this.getVehicleManuf();
  }

  getVehicleManuf(){
    console.log(this.vehicleType)
    var res = this.vehicleService.getManufacturers(this.vehicleType).subscribe((data)=>{
      console.log("Manu from server " + JSON.stringify(data));
      this.manufacturers = data;
    })
  }

  onManuSelected(manu: String){
    console.log(manu);
    // this.models =  this.vehicleService.getModels(manu).
    var res =  this.vehicleService.getModels(manu, this.vehicleType).subscribe((data)=>{
      console.log("Models from server " + JSON.stringify(data));
      this.models = data;
    })
  }

  onTPLClick(){
    this.showForm2 = true;
    this.plan = "TPL";
    this.amount=this.tpl;
  }

  onComprehensiveClick(){
    this.showForm2 = true;
    this.plan = "COM";
    this.amount=this.comp;
  }

  get f1() { 
    return this.form1.controls; 
  }

  onSubmit1() {
    this.form1Submitted = true;

    const purDate: Date = new Date(this.form1.controls['purchaseDate'].value);

    if (this.form1.invalid) {
        return;
    }else if(this.validForm.notEmpty(this.form1.controls['drivingLicence'].value) || this.validForm.notEmpty(this.form1.controls['registrationNumber'].value) ||
    this.validForm.notEmpty(this.form1.controls['engineNumber'].value) || this.validForm.notEmpty(this.form1.controls['chassisNumber'].value)
    || this.validForm.validDate(purDate)){
      alert("Some Info are invalid");
      return;
    }else{
      this.form1Completed = true;

      this.buy.vehicleType = this.vehicleType;
      this.buy.manufacturer = this.form1.controls['manuf'].value;
      this.buy.model = this.form1.controls['model'].value;
      this.buy.drivingLicense = this.form1.controls['drivingLicence'].value;
      this.buy.purchaseDate = this.form1.controls['purchaseDate'].value;
      this.buy.regNumber = this.form1.controls['registrationNumber'].value;
      this.buy.engineNumber = this.form1.controls['engineNumber'].value;
      this.buy.enginePower = this.form1.controls['enginePower'].value;
      this.buy.chassisNumber = this.form1.controls['chassisNumber'].value;
      
      const currentDate : Date= new Date();
      const purchase: Date = new Date(this.form1.controls['purchaseDate'].value);

      var prem=new Premium();
      prem.age=(currentDate.getTime()-purchase.getTime())/31560000000;
      prem.enginePower=this.form1.controls['enginePower'].value;
      prem.type=this.vehicleType;
      prem.model=this.form1.controls['model'].value;

      var resultP=new ResultPremium();

        let res = this.calcService.calculatePremium(prem).subscribe(data=>{
          resultP=data;
            this.tpl=resultP.resultTpl;
            this.comp=resultP.resultComp;
      // this.router.navigateByUrl("/buy-policy");

      // To direct user to other pafe
      // this.router.navigateByUrl("/buy-policy");
      //                                URL
    }
        )
  }
}

  get f2() { 
    return this.form2.controls; 
  }
  onSubmit2() {
    this.form2Submitted = true;

    if (this.form2.invalid) {
        return;
    }else{
      this.buy.plan = this.plan;
      this.buy.amount=this.amount;
      this.buy.planDuration = this.form2.controls['planYear'].value;
      var userId = sessionStorage.getItem("customerId");
      console.log("call server " + userId);
      var response = this.buyService.buyPolicy(this.buy, userId)
      .subscribe((policyNumber)=>{
          alert("Policy Id : " + policyNumber);
          this.router.navigate(['user-profile']);
      })
    }
  }
}