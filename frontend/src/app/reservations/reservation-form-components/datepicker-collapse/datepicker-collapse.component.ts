import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'datepicker-collapse',
    standalone: true,
    templateUrl: './datepicker-collapse.component.html',
  // imports: [NgbCollapseModule, NgbdDatePickerPopupComponent, CommonModule]
  imports: [NgbCollapseModule, CommonModule]
})
export class DatePickerCollapseComponent {
  public isCollapsed = true;
  public hasBeenActivated = false;
}
