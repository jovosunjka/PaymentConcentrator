import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCreatedTransactionComponent } from './show-created-transaction.component';

describe('ShowCreatedTransactionComponent', () => {
  let component: ShowCreatedTransactionComponent;
  let fixture: ComponentFixture<ShowCreatedTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCreatedTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCreatedTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
