import { Component } from '@angular/core';

@Component({
  selector: 'reservations-main',
  templateUrl: './reservations-main.component.html',
  styleUrls: ['./reservations-main.component.less']
})
export class ReservationsMainComponent {
  theNumber!: number;

  ohShownNumber(theNumber: number): void {
    console.log("THETHIGBINbjn")
    this.theNumber = theNumber
  }

  test(): void {
    console.log("hello from main.ts");
  }
}
