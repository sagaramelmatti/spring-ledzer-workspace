import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountgroupListComponent } from './accountgroup-list.component';

describe('AccountgroupListComponent', () => {
  let component: AccountgroupListComponent;
  let fixture: ComponentFixture<AccountgroupListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountgroupListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountgroupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
