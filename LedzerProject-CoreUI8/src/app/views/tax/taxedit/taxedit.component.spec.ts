import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaxeditComponent } from './taxedit.component';

describe('TaxeditComponent', () => {
  let component: TaxeditComponent;
  let fixture: ComponentFixture<TaxeditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaxeditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaxeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
