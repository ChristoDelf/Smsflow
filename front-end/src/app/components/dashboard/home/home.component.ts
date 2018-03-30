import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/user";
import {AdminService} from "../../../services/admin.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    user: User;
    users: User[];

    constructor( ) { }

    ngOnInit() {
    }

}
