import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountgroupEditComponent } from './accountgroup-edit.component';

describe('AccountgroupEditComponent', () => {
  let component: AccountgroupEditComponent;
  let fixture: ComponentFixture<AccountgroupEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountgroupEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountgroupEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
