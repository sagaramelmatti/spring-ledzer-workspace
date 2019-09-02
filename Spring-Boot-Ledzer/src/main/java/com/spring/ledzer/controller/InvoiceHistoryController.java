package com.spring.ledzer.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.InvoiceHistoryDTO;
import com.spring.ledzer.report.strategy.InvoiceHistoryReportStrategy;
import com.spring.ledzer.report.strategy.ReportGenerateContext;
import com.spring.ledzer.repository.AccountRepository;
import com.spring.ledzer.repository.InvoiceHistoryRepository;
import com.spring.ledzer.service.InvoiceHistoryService;
import com.spring.ledzer.util.ExcelGenerator;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InvoiceHistoryController {

	@Autowired
	AccountRepository repository;

	 @Autowired
	 InvoiceHistoryRepository invoiceHistoryRepository;
	 
	 @Autowired
	 InvoiceHistoryService invoiceHistoryService;
	 
	 @Autowired
	 ReportGenerateContext reportGenerateContext;
	 
	 @GetMapping("/invoicesaleshistory")
		public List<InvoiceHistoryDTO> getAllInvoices() throws ResourceNotFoundException {
			List<InvoiceHistoryDTO> invoices = invoiceHistoryService.getInvoicesSaleHistory();
	        return invoices;
	}

    @GetMapping(value = "/reports/invoicesaleshistory/pdf",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> invoiceSalesHistoryReport() throws IOException, ResourceNotFoundException 
    {
    	//ReportBuilder reportBuilder = new InvoiceHistoryPdfReportBuilder();
    	//ReportGenerator reportGenerator =  new ReportGenerator(reportBuilder);
    	
    	List<InvoiceHistoryDTO> invoices = invoiceHistoryService.getInvoicesSaleHistory();
    	
    	String[] header = new String[] { "ID", "Invoice No", "Invoice Date","Customer Name","Invoice Amount","Amount Paid","Amount Due","Tax","Payment Mode"};
    	String type="pdf";
    	
    	reportGenerateContext.setReportGenerateStrategy(new InvoiceHistoryReportStrategy());
    	
    	ByteArrayInputStream bis = reportGenerateContext.buildReport(header, type, invoices);
    	
    	//ByteArrayInputStream bis =  reportGenerator.generateReport(header, footer, type, invoices);
    	
    	/*
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(invoices);
        */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=saleshistoryinvoicereport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    
    @GetMapping(value = "/reports/invoicesaleshistory/excel",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<InputStreamResource> invoiceSalesHistoryExcelReport() throws IOException, ResourceNotFoundException {
        List<InvoiceHistoryDTO> customers = (List<InvoiceHistoryDTO>) invoiceHistoryService.getInvoicesSaleHistory();
    
	    ByteArrayInputStream in = ExcelGenerator.customersToExcel(customers);
	    // return IOUtils.toByteArray(in);
	    
	    HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=saleshistoryinvoicereport.xlsx");
	    
	     return ResponseEntity
	                  .ok()
	                  .headers(headers)
	                  .body(new InputStreamResource(in));
	     }
	
}
