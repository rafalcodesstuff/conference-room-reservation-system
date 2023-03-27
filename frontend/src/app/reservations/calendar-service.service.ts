import { Injectable } from '@angular/core';
import { CalendarEvent } from 'calendar-utils';
import { ReservationResponse } from './reservations-calendar/response-reservation'
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor() { }

  // Observable calendar sources
  private reservationAddedAnnouncedSource = new Subject<any>();
  private reservationAddedConfirmedSource = new Subject<any>();

  // Observable calendar streams
  reservationAddedAnnounced$ = this.reservationAddedAnnouncedSource.asObservable();
  reservationAddedConfirmed$ = this.reservationAddedConfirmedSource.asObservable();

  // Service message commands
  announceAddedReservation(reservation: any) {
    this.reservationAddedAnnouncedSource.next(reservation);
  }

  confirmAddedReservation(confirmation: any) {
    this.reservationAddedConfirmedSource.next(confirmation);
  }
}
