import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemWiseSaleHistoryComponent } from './item-wise-sale-history.component';

describe('ItemWiseSaleHistoryComponent', () => {
  let component: ItemWiseSaleHistoryComponent;
  let fixture: ComponentFixture<ItemWiseSaleHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemWiseSaleHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemWiseSaleHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
