import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
Input,
OnInit,
ChangeDetectorRef,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
} from 'date-fns';
import { Subject, Subscription } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import { CalendarService } from '../calendar-service.service';

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

@Component({
  selector: 'reservations-calendar',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [
    `
      h3 {
        margin: 0 0 10px;
      }

      pre {
        background-color: #f5f5f5;
        padding: 15px;
      }
    `,
  ],
  templateUrl: 'reservations-calendar.component.html',
})
export class ReservationsCalendarComponent {
  @ViewChild('modalContent', { static: true }) modalContent!: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  isConfirmingEditing = false
  isConfirmingDelete = false

  modalData!: {
    action: string;
    event: CalendarEvent;
  };


  actions: CalendarEventAction[] = [
    {
      label: "",
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();

  events: CalendarEvent[] = [
    {
      start: new Date("2023-03-16T11:00:00.000Z"),
      end: new Date("2023-03-16T11:00:00.000Z"),
      title: "asdf (All Day Event)",
      allDay: true,
      color: {
        "primary": "#ad2121",
        "secondary": "#FAE3E3"
      }
    }
  ];

  activeDayIsOpen: boolean = true;

  confirmed = false;
  announced = false;
  private subscription!: Subscription;
  constructor(private modal: NgbModal, private reservationService: CalendarService, private changeDetectorRef: ChangeDetectorRef) { 
    this.subscription = this.reservationService.reservationAddedAnnounced$.subscribe(
      reservation => {
        this.confirmed = true;
        this.announced = true;

        this.addEvent(reservation);
        this.resetValue();
      }
    );
  }

  value!: string;
  resetValue() {
    this.value = "____TempValue____";
    this.changeDetectorRef.detectChanges();
    this.value = "";
  }

  confirm() {
    this.confirmed = true;
    this.reservationService.confirmAddedReservation(this.events[-1]);
  }

  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(event: CalendarEvent): void {
    this.events = [
      ...this.events, event
      // {
      //   title: 'New event',
      //   start: startOfDay(new Date()),
      //   end: endOfDay(new Date()),
      //   color: colors['red'],
      //   draggable: true,
      //   resizable: {
      //     beforeStart: true,
      //     afterEnd: true,
      //   },
      // },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}