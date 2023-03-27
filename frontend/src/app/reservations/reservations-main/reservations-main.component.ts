import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../../user-service.service';
import { Component } from '@angular/core';

@Component({
  selector: 'reservations-main',
  templateUrl: './reservations-main.component.html',
  styleUrls: ['./reservations-main.component.less']
})
export class ReservationsMainComponent {
  switchFormType = "add-form";
  navActive = "add-form";
  greetingUser = localStorage.getItem('username');

  constructor(private http: HttpClient, private userService: UserService, private router: Router) {}

  toggleSwitchFormType(type: string): void {
    this.switchFormType = type;
    this.navActive = type;
  }

  logout() {
    this.http.get('http://localhost:8080/api/logout').subscribe({
      next: (res) => {
        sessionStorage.removeItem('token');
        localStorage.removeItem('username');
        this.router.navigate(['/login']);
      },
    })
  }
}
