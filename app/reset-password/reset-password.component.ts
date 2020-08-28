import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MustMatch } from '../validator';
import { Router } from '@angular/router';
import { ResetPassword } from './reset';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  registerForm: FormGroup;
    submitted = false;

    constructor(private formBuilder: FormBuilder,private router: Router,private loginService: LoginService) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            confirmPassword: ['', Validators.required],
        }, {
            validator: MustMatch('password', 'confirmPassword')
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
          var reset=new ResetPassword();
          reset.email=this.registerForm.controls['email'].value;
          reset.password=this.registerForm.controls['password'].value;

          let res = this.loginService.forgotPassword(reset).subscribe((data)=>{
            if(data!=false){
              alert("Password changed successfully");
             this.router.navigateByUrl("/login");
            }else
            alert("Email Id hasn't registered yet");

        // display form values on success
      //  alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value, null, 4));
    }
          )
  }

}
}
