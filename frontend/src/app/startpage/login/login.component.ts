import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent {

  loginError = false;
  model: any = {};
  sessionId: any = "";

  constructor(
      private router: Router,
      private http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  login() {
    let url = 'http://localhost:8080/api/login';
    this.http.post<any>(url, {
      username: this.model.username,
      password: this.model.password
    }).subscribe({
      next: (res) => {
        this.sessionId = res.sessionId;

        localStorage.setItem('username', this.model.username);

        sessionStorage.setItem(
          'token', this.sessionId
        );
        this.router.navigate(['/calendar'])
      },
      error: (e) => {
        this.loginError = true
      },
    });
  }
}
