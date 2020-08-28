import { Component, OnInit } from '@angular/core';
import { Login } from '../login/login';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  isLogged:number = 0;

  constructor(private formBuilder: FormBuilder,private router: Router, private loginService: LoginService) { }

  ngOnInit() {

    this.isLogged= parseInt(sessionStorage.getItem("isLogged"));
     if(this.isLogged>0){
       this.router.navigateByUrl("/home"); 
     }

      this.registerForm = this.formBuilder.group({
          email: ['', [Validators.required, Validators.email]],
          password: ['', Validators.required]
      });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.registerForm.invalid) {
          return;
      }else{

        var login  = new Login();
        login.email = this.registerForm.controls['email'].value;
        login.password = this.registerForm.controls['password'].value;
        alert(this.registerForm.controls['email'].value+ " " + this.registerForm.controls['password'].value );      

          let res = this.loginService.adminLogin(login).subscribe((data)=>{
          if(data!=null){
            alert("Customer detail from server " + JSON.stringify(data));
            sessionStorage.setItem('customerName', data.name);
            sessionStorage.setItem('customerId', data.email);
            sessionStorage.setItem('isLogged', "10");
            alert("Session customerId :"+sessionStorage.getItem("customerId"));
            this.router.navigate(['/admin-dashboard']);
          }else{
            alert("Some credentials are Incorrect");
          }   
      })
   }  
  }
}
