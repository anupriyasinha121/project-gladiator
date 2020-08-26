import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminComponent } from './admin/admin.component';
import { ClaimInsuranceComponent } from './claim-insurance/claim-insurance.component';
import { BuyInsuranceComponent } from './buy-insurance/buy-insurance.component';
import { RenewInsuranceComponent } from './renew-insurance/renew-insurance.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AboutUsComponent } from "./about-us/about-us.component";
import { FAQComponent } from "./faq/faq.component";
import { ContactUsComponent} from "./contact-us/contact-us.component";
import { HomeComponent } from "./home/home.component";
import { CalculateInsuranceComponent } from './calculate-insurance/calculate-insurance.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import { ForgotPasswordComponent } from './forgotpassword/forgotpassword.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { LoginComponent } from './login/login.component'
import { RegisterComponent} from './register/register.component'

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'contact-us', component: ContactUsComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'faq', component: FAQComponent,},
  {path: 'nav-bar', component: NavBarComponent },
  {path: 'buy-policy', component: BuyInsuranceComponent},
  {path: 'claim-policy', component: ClaimInsuranceComponent},
  {path: 'renew-policy', component: RenewInsuranceComponent},
  {path: 'calculate-premium', component: CalculateInsuranceComponent},
  {path: 'user-profile', component: UserProfileComponent},
  {path:'forgotPassword',component:ForgotPasswordComponent},
  {path:'resetPassword',component:ResetPasswordComponent},
  {path:'login',component:LoginComponent},
  {path:'admin-login',component:AdminComponent},
  {path:'register',component:RegisterComponent},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
