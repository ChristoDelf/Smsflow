import { NgModule } from '@angular/core';
import {HomeComponent} from "./home/home.component";
import {NavComponent} from "./nav/nav.component";
import {AuthGuard} from "../../guards/auth.guard";
import {BrowserModule} from "@angular/platform-browser";
import {SidebarComponent} from "../shared/sidebar/sidebar";
import {TopNavComponent} from "../shared/topnav/topnav";
import {DashboardComponent} from "./dashboard.component";
import {RouterModule} from "@angular/router";
import { UserlistComponent } from './userlist/userlist.component';


@NgModule({
    declarations:[
        DashboardComponent,
        HomeComponent,
        NavComponent,
        TopNavComponent,
        SidebarComponent,
        UserlistComponent
    ],
    imports: [
        BrowserModule,
        RouterModule
    ],
    providers: [
        AuthGuard
    ],
    bootstrap: [],
    exports: [
        HomeComponent,
        NavComponent,
        TopNavComponent,
        SidebarComponent,
        UserlistComponent
    ]
})
export class DashboardModule { }