import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) { }

  public registerUser(user: User) {
    return this.http.post('/api/user/create', user);
  }

}
