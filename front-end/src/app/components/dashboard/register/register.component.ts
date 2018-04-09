import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {}

    changeCountry(select: HTMLSelectElement) {
        console.log(select.value)
    }

    public onSubmit(loginForm: NgForm):void {
        if(loginForm.valid) {
            this.authService.login(loginForm.value).subscribe(
                res => {
                    this.router.navigate(['']);
                }
            );
        }
    }
}
