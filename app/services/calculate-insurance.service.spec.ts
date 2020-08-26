import { TestBed } from '@angular/core/testing';

import { CalculateInsuranceService } from './calculate-insurance.service';

describe('CalculateInsuranceService', () => {
  let service: CalculateInsuranceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateInsuranceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
