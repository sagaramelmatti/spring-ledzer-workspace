import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatgoryEditComponent } from './catgory-edit.component';

describe('CatgoryEditComponent', () => {
  let component: CatgoryEditComponent;
  let fixture: ComponentFixture<CatgoryEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatgoryEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatgoryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
