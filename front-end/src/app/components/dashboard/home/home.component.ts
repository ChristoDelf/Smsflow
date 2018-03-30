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

    constructor(private adminService: AdminService, private authService: AuthService) { }

    ngOnInit() {
        this.user = this.authService.getCurrentUser();
        this.loadUsers();
    }


    private loadUsers(): void {
        this.adminService.getUsers().subscribe(
            res => this.users = res/*.map(u => {
                u.authorities = u.authorities.map(a => a.authority).join(', ');
                return u;*/
            )
    }

    get isAdmin(): boolean {
        return this.user != null && this.user.authorities.indexOf('ROLE_ADMIN') > -1;
    }

}
