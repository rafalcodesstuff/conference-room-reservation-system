import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReservationsModule } from "./reservations/reservations.module";
import { StartpageModule } from './startpage/startpage.module';
import { CalendarService } from './reservations/calendar-service.service';
import { UserService } from './user-service.service';
import { RequestInterceptor } from "./request.interceptor";


@NgModule({
    declarations: [
        AppComponent,
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true},
        CalendarService, 
        UserService
    ],
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
