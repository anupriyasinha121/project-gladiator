import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { Login } from './login';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  registerForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,private router: Router, private loginService: LoginService) { }

  ngOnInit() {

     if(sessionStorage.getItem("customerId")!=null){
       this.router.navigateByUrl("/home"); 
     }
   
    this.registerForm = this.formBuilder.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required]
    } );
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

          let res = this.loginService.userLogin(login).subscribe((data)=>{
          if(data!=null){
            alert("Customer detail from server " + JSON.stringify(data));
            sessionStorage.setItem('customerName', data.name);
            sessionStorage.setItem('customerId', data.email);
            alert("Session customerId :"+sessionStorage.getItem("customerId"));
            this.router.navigate(['/user-profile']);
          }else{
            alert("Some credentials are Incorrect");
          }   
        })

      // display form values on success
      //alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value, null, 4));
  }

  
}
}
