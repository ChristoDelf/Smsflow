import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Route, RouterModule} from '@angular/router';
import {AppComponent} from "./components/app.component";
import {LoginComponent} from './components/login/login.component';
import {AuthService} from "./services/auth.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AdminService} from "./services/admin.service";
import {AuthInterceptor} from "./services/auth.interceptor";
import {AuthGuard} from "./guards/auth.guard";
import {RegisterComponent} from './components/register/register.component';
import {DashboardComponent} from './components/dashboard';
import {DashboardModule} from "./components/dashboard/dashboard.module";
import {HomeComponent} from "./components/dashboard/home/home.component";
import {UserlistComponent} from "./components/dashboard/userlist/userlist.component";

const routes: Route[] = [
    { path: '', component: LoginComponent },
    { path: 'signup', component: RegisterComponent },
    { path: 'dashboard',
        component: DashboardComponent,
        canActivate:[AuthGuard],
        children: [
            { path: 'home', component: HomeComponent },
            { path: 'userlist', component: UserlistComponent }
        ]
    }
];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        RegisterComponent
    ],
    imports: [
        BrowserModule,
        DashboardModule,
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
            multi: true
        },
        AuthGuard
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
