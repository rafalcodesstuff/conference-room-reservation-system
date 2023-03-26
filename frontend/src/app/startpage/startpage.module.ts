import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReservationsModule } from "../reservations/reservations.module";


@NgModule({
    declarations: [
        LoginComponent,
        RegisterComponent,
        MainComponent
    ],
    exports: [
        LoginComponent,
        RegisterComponent,
        MainComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        ReservationsModule
    ]
})
export class StartpageModule { }
