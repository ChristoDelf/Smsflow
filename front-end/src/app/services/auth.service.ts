import {EventEmitter, Injectable} from '@angular/core';
import {User} from "../models/user";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {tap} from "rxjs/operators";

@Injectable()
export class AuthService {

    private user: User = null;
    public connectedUser: EventEmitter<User> = new EventEmitter<User>();

    constructor(private http: HttpClient) {
        this.connectedUser.subscribe(
            user => this.user = user,
            err => this.user = null
        )
    }

    public login(user: User): Observable<HttpResponse<Object>> {
        return this.http.post('/api/login', user, {observe: 'response'}).pipe(
            tap(res => {
                    const token = res.headers.get('Authorization').substr(7);
                    localStorage.setItem('token', token);
                    this.whoAmI();
                },
                err => {
                    this.connectedUser.emit(null);
                })
        );
    }

    public whoAmI(): void {

        if (localStorage.getItem('token') != null){
            this.http.get<User>('/api/user/whoami').subscribe(
                user => {
                    this.connectedUser.emit(user);
                },
                err => {
                    this.connectedUser.emit(null);
                }
            );
        }
    }

    public getCurrentUser(): User {
        return this.user;
    }

    public logout(): void {
        localStorage.removeItem('token');
        this.connectedUser.emit(null);
    }

    public isLoggedIn() {
        return this.user != null;
    }
}
