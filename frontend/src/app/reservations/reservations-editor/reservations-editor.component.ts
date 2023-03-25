import { Component } from '@angular/core';

@Component({
  selector: 'reservations-editor',
  templateUrl: './reservations-editor.component.html',
  styleUrls: ['./reservations-editor.component.less']
})
export class ReservationsEditorComponent {
  protected isChecked = true;
  time = { hour: 13, minute: 30 };
}


