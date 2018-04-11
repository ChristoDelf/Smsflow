import { NgModule } from '@angular/core';
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "../../guards/auth.guard";
import {BrowserModule} from "@angular/platform-browser";
import {SidebarComponent} from "../shared/sidebar/sidebar";
import {TopNavComponent} from "../shared/topnav/topnav";
import {DashboardComponent} from "./dashboard.component";
import {RouterModule} from "@angular/router";
import { UserlistComponent } from './userlist/userlist.component';
import {RegisterComponent} from "./register/register.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
    declarations:[
        DashboardComponent,
        HomeComponent,
        TopNavComponent,
        SidebarComponent,
        UserlistComponent,
        RegisterComponent
    ],
    imports: [
        BrowserModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [
        AuthGuard
    ],
    bootstrap: [],
    exports: [
        HomeComponent,
        TopNavComponent,
        SidebarComponent,
        UserlistComponent,
        RegisterComponent
    ]
})
export class DashboardModule { }