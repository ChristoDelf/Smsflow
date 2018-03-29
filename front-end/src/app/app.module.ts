import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Route, RouterModule} from '@angular/router';
import {AppComponent} from "./components/app.component";
import { NavComponent } from './components/nav/nav.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import {AuthService} from "./services/auth.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AdminService} from "./services/admin.service";
import {AuthInterceptor} from "./services/auth.interceptor";

const routes: Route[] = [
    {path:'login', component:LoginComponent},
    {path:'', component:HomeComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    HomeComponent
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
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }