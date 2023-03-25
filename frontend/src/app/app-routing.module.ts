import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReservationsAddFormComponent } from './reservations/reservations-add-form/reservations-add-form.component';
import { ReservationsEditorComponent } from './reservations/reservations-editor/reservations-editor.component';
import { ReservationsMainComponent } from './reservations/reservations-main/reservations-main.component';
import { LoginComponent } from './startpage/login/login.component';
import { RegisterComponent } from './startpage/register/register.component'

const routes: Routes = [
  { path: "calendar", component: ReservationsMainComponent },
  { path: "calendar/add", component: ReservationsAddFormComponent },
  { path: "calendar/edit", component: ReservationsEditorComponent },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
