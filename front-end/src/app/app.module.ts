import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Route, RouterModule} from '@angular/router';
import {AppComponent} from "./components/app.component";
import { NavComponent } from './components/dashboard/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/dashboard/home/home.component';
import {AuthService} from "./services/auth.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AdminService} from "./services/admin.service";
import {AuthInterceptor} from "./services/auth.interceptor";
import {AuthGuard} from "./guards/auth.guard";
import { RegisterComponent } from './components/register/register.component';

const routes: Route[] = [
    {path:'', component:LoginComponent},
    {path:'home', component:HomeComponent, canActivate:[AuthGuard]}
];

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
      AuthService,
      AdminService,
      {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi:true
      },
      AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
