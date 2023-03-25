import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReservationsModule } from "./reservations/reservations.module";
import { StartpageModule } from './startpage/startpage.module';
import { CalendarService } from './reservations/calendar-service.service';
import { UserService } from './user-service.service';


@NgModule({
    declarations: [
        AppComponent,
    ],
    providers: [CalendarService, UserService],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        CommonModule,
        NgbModule,
        RouterModule,
        HttpClientModule,
        ReservationsModule,
        StartpageModule
    ]
})
export class AppModule { }
