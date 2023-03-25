import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsEditorComponent } from './reservations-editor.component';

describe('ReservationsEditorComponent', () => {
  let component: ReservationsEditorComponent;
  let fixture: ComponentFixture<ReservationsEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationsEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationsEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
