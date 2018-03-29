import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    return: string = '';

    constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.authService.connectedUser.subscribe(
            user => {
                if (user != null) {
                    this.router.navigate(['home'])
                }
            });

        this.route.queryParams.subscribe(params => this.return = params['return'] || '/home');
    }

    public onSubmit(loginForm: NgForm):void {
        if(loginForm.valid) {
            this.authService.login(loginForm.value).subscribe(
                res => {
                    this.router.navigate(['home']);
                }
            );
        }
    }
}
