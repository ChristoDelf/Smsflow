import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import {User} from "../../../models/user";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  user: User;
  users: User[];

  constructor(private adminService: AdminService, private authService: AuthService) { }

  ngOnInit() {
    this.user = this.authService.getCurrentUser();
    this.loadUsers();
  }


    private loadUsers(): void {
        this.adminService.getUsers().subscribe(
            res => this.users = res
        );
    }

    get isAdmin(): boolean {
        return this.user != null && this.user.authorities.indexOf('ROLE_ADMIN') > -1;
    }
}
