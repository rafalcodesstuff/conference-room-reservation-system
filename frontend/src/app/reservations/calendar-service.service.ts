import { Injectable } from '@angular/core';
import { CalendarEvent } from 'calendar-utils';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor() { }

  // Observable calendar sources
  private reservationAddedAnnouncedSource = new Subject<CalendarEvent>();
  private reservationAddedConfirmedSource = new Subject<CalendarEvent>();

  // Observable calendar streams
  reservationAddedAnnounced$ = this.reservationAddedAnnouncedSource.asObservable();
  reservationAddedConfirmed$ = this.reservationAddedConfirmedSource.asObservable();

  // Service message commands
  announceAddedReservation(reservation: CalendarEvent) {
    this.reservationAddedAnnouncedSource.next(reservation);
  }

  confirmAddedReservation(confirmation: CalendarEvent) {
    this.reservationAddedConfirmedSource.next(confirmation);
  }
}
