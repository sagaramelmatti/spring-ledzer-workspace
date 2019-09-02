import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerWiseSaleHistoryComponent } from './customer-wise-sale-history.component';

describe('CustomerWiseSaleHistoryComponent', () => {
  let component: CustomerWiseSaleHistoryComponent;
  let fixture: ComponentFixture<CustomerWiseSaleHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerWiseSaleHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerWiseSaleHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
