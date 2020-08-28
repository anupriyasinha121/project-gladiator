import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MustMatch } from '../validator';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Customerregister } from './customerregister';
import { RegisterService } from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;
  isLogged:number = 0;
  constructor(private formBuilder: FormBuilder,private router: Router, private registerService: RegisterService) { }

  ngOnInit() {

    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
     if(this.isLogged>0){
       this.router.navigateByUrl("/home"); 
    }

      this.registerForm = this.formBuilder.group({
          name: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          contact: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
          address: ['', Validators.required],
          dob: ['', Validators.required],
          password: ['', [Validators.required, Validators.minLength(6)]],
          confirmPassword: ['', Validators.required]
      },{
        validator: MustMatch('password', 'confirmPassword')
    });
  }
  

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;
      const currentDate : Date= new Date();
      const dateOfBirth: Date = new Date(this.registerForm.controls['dob'].value);
      console.log(currentDate);
      console.log(dateOfBirth);

      // stop here if form is invalid
      if (this.registerForm.invalid) {                    
          return;
      }else if(this.registerForm.controls['name'].value.trim().length==0||this.registerForm.controls['address'].value.trim().length==0){
          alert('Some fields are Null');
          return;
      }else if(currentDate.getTime()-dateOfBirth.getTime()<568080000000){
          alert('Age can not be less than 18 years');
          return;
      }else{

        var customerregister  = new Customerregister();
        customerregister.email = this.registerForm.controls['email'].value;
        customerregister.name = this.registerForm.controls['name'].value;
        customerregister.password = this.registerForm.controls['password'].value;
        customerregister.address = this.registerForm.controls['address'].value;
        customerregister.dob = this.registerForm.controls['dob'].value;
        customerregister.contact = this.registerForm.controls['contact'].value;

        let res = this.registerService.registration(customerregister).subscribe((user)=>{
        if(user==null){
          alert("User Already Exist");
        }else{
          // alert("Registration Detail " + JSON.stringify(user))
          sessionStorage.setItem('customerName', user.name);
          sessionStorage.setItem('customerId', user.email);
          // alert("After setting session " + sessionStorage.getItem("customerId"));
          this.router.navigateByUrl("/home");
        }
                           
      })
    }       
  }     
}