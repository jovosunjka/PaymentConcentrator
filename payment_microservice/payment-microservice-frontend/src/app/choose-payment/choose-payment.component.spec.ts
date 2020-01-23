import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoosePaymentComponent } from './choose-payment.component';

describe('ChoosePaymentComponent', () => {
  let component: ChoosePaymentComponent;
  let fixture: ComponentFixture<ChoosePaymentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChoosePaymentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoosePaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
