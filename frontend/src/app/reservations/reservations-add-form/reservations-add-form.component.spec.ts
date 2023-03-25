import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsAddFormComponent } from './reservations-add-form.component';

describe('ReservationsAddFormComponent', () => {
  let component: ReservationsAddFormComponent;
  let fixture: ComponentFixture<ReservationsAddFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationsAddFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationsAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
