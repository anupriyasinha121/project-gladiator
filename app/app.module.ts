import { BuyService } from './services/buy.service';
import { ClaimService } from './services/claim.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuyInsuranceComponent } from './buy-insurance/buy-insurance.component';
import { ClaimInsuranceComponent } from './claim-insurance/claim-insurance.component';
import { RenewInsuranceComponent } from './renew-insurance/renew-insurance.component';
import { AboutUsComponent } from "./about-us/about-us.component";
import { FAQComponent } from "./faq/faq.component";
import { ContactUsComponent} from "./contact-us/contact-us.component";
import { HomeComponent } from "./home/home.component";
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CalculateInsuranceComponent } from './calculate-insurance/calculate-insurance.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ForgotPasswordComponent } from './forgotpassword/forgotpassword.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { LoginComponent } from './login/login.component'
import { RegisterComponent} from './register/register.component'
import { HttpClientModule } from "@angular/common/http";
import { RegisterService } from './register.service';
import { LoginService} from './login.service';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminComponent } from './admin/admin.component';
import { PaymentComponent } from './payment/payment.component';

@NgModule({
  declarations: [
    AppComponent,
    BuyInsuranceComponent,
    ClaimInsuranceComponent,
    RenewInsuranceComponent,   
    HomeComponent,
    ContactUsComponent,
    AboutUsComponent,
    FAQComponent,
    NavBarComponent,
    CalculateInsuranceComponent,
    UserProfileComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    LoginComponent,
    RegisterComponent,
    AdminLoginComponent,
    PageNotFoundComponent,
    AdminComponent,
    PaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ClaimService,
    BuyService,
    RegisterService,
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }