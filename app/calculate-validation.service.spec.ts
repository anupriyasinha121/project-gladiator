import { TestBed } from '@angular/core/testing';

import { CalculateValidationService } from './calculate-validation.service';

describe('CalculateValidationService', () => {
  let service: CalculateValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
