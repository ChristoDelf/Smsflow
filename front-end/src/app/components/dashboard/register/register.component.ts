import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterService} from "../../../services/register.service";
import {PasswordValidation} from "../../../validation/PasswordValidation";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  countries: string[] = [
      'Belgium', 'France', 'Netherlands'
  ];

  registerFormGroup: FormGroup;

  constructor(private registerService: RegisterService, private router: Router, private builder: FormBuilder) {
      this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
      this.registerFormGroup = this.builder.group({
          'companyName': [ '', Validators.compose([Validators.required, Validators.minLength(6)]) ],
          'nameOfContact': [ '', Validators.required ],
          'username': [ '', Validators.required ],
          'password': [ '', Validators.required ],
          'confirmPassword': [
              '', [ Validators.required, PasswordValidation.MatchPassword ]
          ]
      });
  }

    public onSubmit(registerForm: NgForm):void {
        if(registerForm.valid) {
            this.registerService.registerUser(registerForm.value).subscribe(
                res => {
                    this.router.navigateByUrl('/dashboard/userlist');
                }
            );
        }
    }
}
