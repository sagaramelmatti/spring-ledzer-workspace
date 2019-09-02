import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatgoryListComponent } from './catgory-list.component';

describe('CatgoryListComponent', () => {
  let component: CatgoryListComponent;
  let fixture: ComponentFixture<CatgoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatgoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatgoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
