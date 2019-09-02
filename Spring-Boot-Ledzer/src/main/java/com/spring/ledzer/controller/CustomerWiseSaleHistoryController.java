package com.spring.ledzer.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.CustomerWiseSaleHistoryDTO;
import com.spring.ledzer.model.dto.ReportSearchDataDTO;
import com.spring.ledzer.report.strategy.CustomerWiseSaleHistoryReportStrategy;
import com.spring.ledzer.report.strategy.ReportGenerateContext;
import com.spring.ledzer.service.CustomerWiseSaleHistoryService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reports")
public class CustomerWiseSaleHistoryController {

	 @Autowired
	 CustomerWiseSaleHistoryService customerWiseSaleHistoryService;
	 
	 @Autowired
	 ReportGenerateContext reportGenerateContext;
	 
	 
	 @PostMapping("/customerwisesalehistory")
	    public List<CustomerWiseSaleHistoryDTO> customerWiseSalesHistoryReport(@Valid @RequestBody ReportSearchDataDTO reportSearchDataDTO) throws ResourceNotFoundException {
		 
		 List<CustomerWiseSaleHistoryDTO> invoices = customerWiseSaleHistoryService.getCustomerWiseSaleHistoryList(reportSearchDataDTO.getCustomerId(),reportSearchDataDTO.getFromDate(),reportSearchDataDTO.getToDate());
		 return invoices;
	    }

	 @PostMapping(value = "/customerwisesalehistory/pdf",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customerWiseSalesHistoryPDFReport(@Valid @RequestBody ReportSearchDataDTO reportSearchDataDTO) throws IOException, ResourceNotFoundException 
    {
        //ByteArrayInputStream bis = PDFGenerator.itemWiseSalePDFReport(customerWiseSaleHistoryDTO_list);
        
        List<CustomerWiseSaleHistoryDTO> customerWiseSaleHistoryDTO_list = customerWiseSaleHistoryService.getCustomerWiseSaleHistoryList(reportSearchDataDTO.getCustomerId(),reportSearchDataDTO.getFromDate(),reportSearchDataDTO.getToDate());
    	
    	String[] header = new String[] { "ID", "Invoice Name", "Customer Name","Sale Date","Invoice Total","Discount","Tax","Amount Paid","Amount Due","Account Name","Payment Mode Name"};
    	String type="pdf";
    	
    	reportGenerateContext.setReportGenerateStrategy(new CustomerWiseSaleHistoryReportStrategy());
    	
    	ByteArrayInputStream bis = reportGenerateContext.buildReport(header, type, customerWiseSaleHistoryDTO_list);
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customerwiseshistoryinvoicereport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
	 @PostMapping(value = "/reports/customerwisesalehistory/excel",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<InputStreamResource> customerWiseSalesHistoryExcelReport(@Valid @RequestBody ReportSearchDataDTO reportSearchDataDTO) throws IOException, ResourceNotFoundException {
    
    	Long customerId = 1l;
	   // ByteArrayInputStream in = ExcelGenerator.item_wise_sale_history_Excel(customerWiseSaleHistoryDTO_list);
	    // return IOUtils.toByteArray(in);
	    
	    
		List<CustomerWiseSaleHistoryDTO> customerWiseSaleHistoryDTO_list = customerWiseSaleHistoryService.getCustomerWiseSaleHistoryList(reportSearchDataDTO.getCustomerId(),reportSearchDataDTO.getFromDate(),reportSearchDataDTO.getToDate());
    	
    	String[] header = new String[] { "ID", "Item Name", "UOM","Rate","Quantity","Amount","Sale Date","Invoice Name"};
    	String type="pdf";
    	
    	reportGenerateContext.setReportGenerateStrategy(new CustomerWiseSaleHistoryReportStrategy());
    	
    	ByteArrayInputStream in = reportGenerateContext.buildReport(header, type, customerWiseSaleHistoryDTO_list);
	    
	    HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=customerwiseshistoryinvoicereport.xlsx");
	    
	     return ResponseEntity
	                  .ok()
	                  .headers(headers)
	                  .body(new InputStreamResource(in));
	     }
	
}
