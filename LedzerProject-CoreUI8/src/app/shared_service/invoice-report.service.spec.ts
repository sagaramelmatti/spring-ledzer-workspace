import { TestBed } from '@angular/core/testing';

import { InvoiceReportService } from './invoice-report.service';

describe('InvoiceReportService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceReportService = TestBed.get(InvoiceReportService);
    expect(service).toBeTruthy();
  });
});
