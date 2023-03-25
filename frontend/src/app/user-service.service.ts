import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  usersUrl = "http://localhost:8080"

  constructor(private http: HttpClient) {
  }

  public checkIfUserExists(user: User): Observable<User> {
    return this.http.post<User>(this.usersUrl + "/auth/register", user);
  }

  public register(user: User) {
    return this.http.post<User>(this.usersUrl + '/users/add', user);
  }
  
}
