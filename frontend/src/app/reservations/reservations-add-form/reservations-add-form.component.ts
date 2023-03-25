import { Component, OnInit } from '@angular/core';
import { CalendarEvent } from 'angular-calendar';

import { EventColor } from 'calendar-utils';
import { CalendarService } from '../calendar-service.service';
import { AbstractControl, FormBuilder, FormGroup, Validators, ValidationErrors } from '@angular/forms';
import { NgbDateAdapter, NgbDateNativeAdapter } from '@ng-bootstrap/ng-bootstrap';
import { setMinutes, setHours } from 'date-fns';


const agreeableTimesValidator = (control: AbstractControl): ValidationErrors | null => {
  let startTimeHour = control.get("startTime")?.value?.hour
  let startTimeMinute = control.get("startTime")?.value?.minute

  let endTimeHour = control.get("endTime")?.value?.hour
  let endTimeMinute = control.get("endTime")?.value?.minute

  let startTime = Date.parse(`01/01/2000 ${startTimeHour}:${startTimeMinute}:00`)
  let endTime = Date.parse(`01/01/2000 ${endTimeHour}:${endTimeMinute}:00`)

  if (startTime >= endTime) {
    return { badTime: true }
  }
  return null
}

const agreeableDatesValidator = (control: AbstractControl): ValidationErrors | null => {
  let startDate = control.get("startDate")?.value
  let endDate = control.get("endDate")?.value
  
  if (startDate && endDate) {
    if (new Date(startDate) >= new Date(endDate)) {
      return { badDate: true }
    }
  }
  return null
}


const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

interface Indexable {
  [key: string]: any;
}

interface DisabledFormValues extends Indexable {
  firstDate: string,
  secondDate: string,
  firstTime: object,
  secondTime: object
}

@Component({
  selector: 'reservations-add-form',
  templateUrl: './reservations-add-form.component.html',
  styleUrls: ['./reservations-add-form.component.less'],
  providers: [
    { provide: NgbDateAdapter, useClass: NgbDateNativeAdapter },
  ]
})
export class ReservationsAddFormComponent implements OnInit {
  
  isDatepickerCollapsed = true;
  isTimepickerCollapsed = true;
  isAllDayValue = true;
  hasTimeValue = false;
  isMultiDayValue = false;
  endDateValue = null;

  isSecondDateDisabled = true;
  isDatePickersDisabled = false;
  isTimePickersDisabled = false;
  isRecurringPickerDisabled = false;

  editingFormEvent = false;

  datePickersToolTip = '';
  timePickersToolTip = '';

  disabledFormValues: DisabledFormValues = {
    firstDate: '',
    secondDate: '',
    firstTime: {},
    secondTime: {}
  } 

  addReservationForm!: FormGroup;
  reservation!: CalendarEvent;

  constructor(
    private formBuilder: FormBuilder, 
    private reservationService: CalendarService, 
  ) {
    reservationService.reservationAddedAnnounced$.subscribe(reservation => {
      this.reservation = reservation;
    })
  }

  buildAddReservationForm() {
    return this.addReservationForm = this.formBuilder.group({
      reservationName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_-]{1,30}$/)]],
      conferenceRoomName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_-]{1,30}$/)]],
      color: ['red', Validators.required],
      startTime: '', //['', this.timeEmptyValidator],
      endTime: '', //['', this.timeEmptyValidator],
      isAllDay: [true],
      isMultiDay: [false],
      hasTime: [false],
      startDate: { year: 0, month: 0, day: 0 },
      endDate: [null],
    }, { validator: [agreeableTimesValidator, agreeableDatesValidator] } )
  }


  ngOnInit() {
    this.addReservationForm = this.buildAddReservationForm();

    this.addReservationForm.valueChanges.subscribe(data => {
      // this if is here because clearing the values in the form caused an infinite loop
      // this if statement ends the infintite loop
      if (this.editingFormEvent) {
        this.editingFormEvent = false
        return
      }

      this.isAllDayValue = data.isAllDay
      this.isMultiDayValue = data.isMultiDay
      this.hasTimeValue = data.hasTime

      this.toggleFormControls()
    })
  }    

  convertTimeToDate(time:string): number[] {
    let hours: number = this.addReservationForm.get(time)?.value.hour;
    let minutes: number = this.addReservationForm.get(time)?.value.minute;

    if (hours == undefined) {
      hours = 0;
    }
    if (minutes == undefined) {
      minutes = 0;
    }
    return [hours, minutes]
  }

  toggleDisableButton() {

  }

  toggleEndDateValue() {
    if (this.addReservationForm.get("isAllDay")?.value) {
      this.addReservationForm.get("endDate")?.setValue(this.endDateValue);
    } else {
      this.endDateValue = this.addReservationForm.get("endDate")?.value;
      this.addReservationForm.get("endDate")?.setValue(null);
    }
  }

  toggleFormControls(): void {
    if (this.shouldDisableDatePickers()) {
      this.saveDisabledDates()

      this.clearDisabledDatesValue('startDate')
      this.clearDisabledDatesValue('endDate')
      
      this.isDatePickersDisabled = true
      this.setDatePickersToolTip()
    } else {

      if (this.isDatePickersDisabled) { // this catches an infinite loop and other bad things
        this.loadSavedFormValue('startDate')
        this.loadSavedFormValue('endDate')

      }
      this.isDatePickersDisabled = false
      this.setDatePickersToolTip()
    }

    if (this.shouldDisableTimePicker()) {
      this.saveDisabledTime()
      
      this.clearDisabledTimesValue('startTime')
      this.clearDisabledTimesValue('endTime')

      this.isTimePickersDisabled = true
      this.setTimePickerToolTipText()
    } else {

      if (this.isTimePickersDisabled) { // this catches an infinite loop
        this.loadSavedFormValue('startTime')
        this.loadSavedFormValue('endTime')
      }

      this.isTimePickersDisabled = false
      this.setTimePickerToolTipText()
    }
  }

  saveFormValue(key: string, value: string | object): void {
    this.disabledFormValues[key] = value;
  }

  loadSavedFormValue(key: string): void {
    this.editingFormEvent = true // used to stop infitine loop

    let savedFieldName: string 

    switch (key) {
      case 'startDate':
        savedFieldName = 'firstDate'
        break;
      case 'endDate':
        savedFieldName = 'secondDate'
        break;
      case 'startTime':
        savedFieldName = 'firstTime'
        break;
      case 'endTime':
        savedFieldName = 'secondTime'
        break;
      default:
        break;
    }

    this.addReservationForm.get(key)?.setValue(this.disabledFormValues[savedFieldName!])
  }

  saveSecondDate(): void {
    let date = this.addReservationForm.get("endDate")?.value
    if (date !== '') {
      this.saveFormValue('secondDate', date)
    }
  }

  saveDisabledDates(): void {
    let startDate = this.addReservationForm.get("startDate")?.value
    let endDate = this.addReservationForm.get("endDate")?.value

    if (startDate !== '') {
      this.saveFormValue('firstDate', startDate)
    }

    if (endDate !== '') {
      this.saveFormValue('secondDate', endDate)
    }
  }

  saveDisabledTime(): void {
    let startTime = this.addReservationForm.get("startTime")?.value
    let endTime = this.addReservationForm.get("endTime")?.value

    if (startTime.hour !== null && startTime.minute !== null) {
      this.saveFormValue('firstTime', startTime)
    }

    if (endTime.hour !== null && endTime.minute !== null) {
      this.saveFormValue('secondTime', endTime)
    }
  }

  clearDisabledDatesValue(key: string): void {
    this.editingFormEvent = true // used to stop infitine loop
    this.addReservationForm.get(key)?.setValue('')
  }

  clearDisabledTimesValue(key: string): void {
    this.editingFormEvent = true // used to stop infitine loop
    this.addReservationForm.get(key)?.setValue({ hour: null, minute: null})
  }

  clearSecondDate(): void {
    this.addReservationForm.get('endDate')?.setValue(null)
  }

  loadSecondDate(): void {
    let data = this.disabledFormValues["endDate"]
    if (data === '') {
      data = null
    }
    this.addReservationForm.get("endDate")?.setValue(this.disabledFormValues['secondDate'])
  }

  toggleSecondDate(): void {
    let endDate = this.addReservationForm.get("endDate")?.value

    if (this.isSecondDateDisabled) {
      this.isSecondDateDisabled = false
      this.loadSecondDate()
    } else { 
      this.isSecondDateDisabled = true
      this.saveSecondDate()
      this.clearSecondDate()
    }
  }

  shouldDisableDatePickers(): boolean {
    if (!this.isAllDayValue && !this.isMultiDayValue && !this.hasTimeValue) {
      return true
    } else if (!this.isAllDayValue && this.isMultiDayValue) {
      return true
    }
    return false
  }

  setDatePickersToolTip(): void {
    if (!this.isAllDayValue && !this.isMultiDayValue && !this.hasTimeValue) {
      this.datePickersToolTip = 'Must be either an All-Day Reservation or a Reservation with a Time'
    }
    else if (!this.isAllDayValue && this.isMultiDayValue) {
      this.datePickersToolTip = 'Multi-Day Reservation must be an All-Day Reservation (No Time)'
    } else {
      this.datePickersToolTip = ''
    }
  }

  shouldDisableTimePicker(): boolean {
    if (this.isAllDayValue) {
      return true
    }
    else if (this.hasTimeValue && this.isMultiDayValue) {
      return true
    }
    return false
  }

  setTimePickerToolTipText(): void {
    if (this.isAllDayValue) {
      this.timePickersToolTip = 'All-Day Reservation cannot have a Time'
    }
    else if (this.hasTimeValue && this.isMultiDayValue) {
      this.timePickersToolTip = 'Multi-Day Reservation must be an All-Day Reservation (No Time)'
    }
    else if (this.addReservationForm.get('startTime')?.errors?.['timeEmpty'] != null) {
      this.timePickersToolTip = 'Times cannot be empty'
    } else {
      this.timePickersToolTip = ''
    }
  }

  timeEmptyValidator(control: AbstractControl): ValidationErrors | null {
    let startHour = control.value?.hour
    let startMinute = control.value?.minute

    let isTimeEmpty = (startHour == undefined || startHour == null) || (startMinute == undefined || startMinute == null)

    if (isTimeEmpty) {
      return { timeEmpty: true }
    } 
    return null
  }

  announceAdded() {
    let isAllDay = this.isAllDayValue;

    let startDate = new Date(this.addReservationForm.get("startDate")?.value)
    let startDatetime = setMinutes(new Date(startDate), this.addReservationForm.get('startTime')?.value.minute ?? 0)
    startDatetime = setHours(startDatetime, this.addReservationForm.get('startTime')?.value.hour ?? 0)
    
    let endDatetime = setMinutes(new Date(startDate), this.addReservationForm.get('endTime')?.value.minute ?? 0)
    endDatetime = setHours(endDatetime, this.addReservationForm.get('endTime')?.value.hour ?? 0)
    
    let endDate = new Date(this.addReservationForm.get("endDate")?.value)
 // if all day event then add datetime else add date
    
    let endPostValue = (): Date => {
      if (isAllDay) {
        if (this.isMultiDayValue) {
          return endDate
        }
        return startDate; 
      }
      else if (this.hasTimeValue) {
        return startDate;
      }
      return startDate;
    }
  
    const reservation = {
      start: isAllDay ? startDate : startDatetime,
      end: endPostValue(),
      title: this.addReservationForm.get("reservationName")?.value + (isAllDay ? " (All Day Event)" : ''),
      allDay: isAllDay,
      color: { ...colors[this.addReservationForm.get("color")?.value] },
      // resizeable ??
      // draggable ??
    }

    this.reservationService.announceAddedReservation(reservation);
  }
}