import { TestBed } from '@angular/core/testing';

import { PurchaseReportService } from './purchase-report.service';

describe('PurchaseReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PurchaseReportService = TestBed.get(PurchaseReportService);
    expect(service).toBeTruthy();
  });
});
