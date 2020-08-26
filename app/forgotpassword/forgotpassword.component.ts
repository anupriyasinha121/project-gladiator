import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;

  public captchaCode:String = "";
  public txt="";
  constructor(private formBuilder: FormBuilder,private router: Router) { }
  ngOnInit(): void {
    this.generateCaptcha();
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      captcha: ['', Validators.required]
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
       this.router.navigateByUrl("/login");

  // display form values on success
//  alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value, null, 4));
}

  }

  generateCaptcha(){
    let alpha:String[] = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
    this.captchaCode = "";
    for(let i=0;i<5;i++){
      this.captchaCode = this.captchaCode + '' +alpha[Math.floor(Math.random() * alpha.length)];
    }
  }
  CheckValidCaptcha(){
    if (this.captchaCode == this.txt){
alert("Password has changed Successfully");
    }
    else{       
//document.getElementById('error').innerHTML = "Please enter a valid captcha."; 
alert("Please enter a valid captcha.");

    }
}

}
