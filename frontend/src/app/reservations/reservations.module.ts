import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbDatepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTimepickerModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { ReservationsCalendarComponent } from './reservations-calendar/reservations-calendar.component';
import { ReservationsEditorComponent } from './reservations-editor/reservations-editor.component';
import { ReservationsAddFormComponent } from './reservations-add-form/reservations-add-form.component';
import { DatePickerCollapseComponent } from "./reservation-form-components/datepicker-collapse/datepicker-collapse.component";
import { ReservationsMainComponent } from './reservations-main/reservations-main.component';


@NgModule({
  declarations: [
    ReservationsCalendarComponent,
    ReservationsEditorComponent,
    ReservationsAddFormComponent,
    ReservationsMainComponent,
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModalModule,
    NgbDatepickerModule,
    NgbCollapseModule,
    NgbTimepickerModule,
    NgbTooltip,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  exports: [
    ReservationsEditorComponent,
    ReservationsCalendarComponent,
    ReservationsAddFormComponent,
    ReservationsMainComponent,
  ]
})
export class ReservationsModule { 

}

export { ReservationsCalendarComponent };
