import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  usersUrl = "http://localhost:8080"

  constructor(private http: HttpClient) {}

  public register(user: User) {
    return this.http.post<any>(this.usersUrl + '/api/register', user);
  }

  public logout() {
    return this.http.get(this.usersUrl + '/api/logout');
  }

}
