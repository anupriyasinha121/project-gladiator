import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RenewInsuranceComponent } from './renew-insurance.component';

describe('RenewInsuranceComponent', () => {
  let component: RenewInsuranceComponent;
  let fixture: ComponentFixture<RenewInsuranceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenewInsuranceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RenewInsuranceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
