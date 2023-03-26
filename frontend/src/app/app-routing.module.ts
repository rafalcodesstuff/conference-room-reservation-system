import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationGuard } from './authentication.guard';
import { ReservationsMainComponent } from './reservations/reservations-main/reservations-main.component';
import { LoginComponent } from './startpage/login/login.component';
import { RegisterComponent } from './startpage/register/register.component'

const routes: Routes = [
  { path: '', canActivate: [AuthenticationGuard], children: [
    { path: "calendar", component: ReservationsMainComponent },
    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },
  ]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
