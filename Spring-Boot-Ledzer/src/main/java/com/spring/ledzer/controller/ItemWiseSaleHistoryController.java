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
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;
import com.spring.ledzer.report.strategy.ItemWiseSaleHistoryReportStrategy;
import com.spring.ledzer.report.strategy.ReportGenerateContext;
import com.spring.ledzer.service.ItemWiseSaleHistoryService;
import com.spring.ledzer.util.ExcelGenerator;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ItemWiseSaleHistoryController {

	 @Autowired
	 ItemWiseSaleHistoryService itemWiseSaleHistoryService;
	 
	 @Autowired
	 ReportGenerateContext reportGenerateContext;
	 
	 
	@GetMapping("/reports/itemwisesalehistory")
	public List<ItemWiseSaleHistoryDTO> getAllInvoices() throws ResourceNotFoundException 
	 {
			List<ItemWiseSaleHistoryDTO> invoices = itemWiseSaleHistoryService.getItemWiseSaleHistory();
	        return invoices;
	}

    @GetMapping(value = "/reports/itemwisesalehistory/pdf",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> invoiceSalesHistoryReport() throws IOException, ResourceNotFoundException 
    {
    	
        //ByteArrayInputStream bis = PDFGenerator.itemWiseSalePDFReport(itemWiseSaleHistoryDTO_list);
        
        List<ItemWiseSaleHistoryDTO> itemWiseSaleHistoryDTO_list = itemWiseSaleHistoryService.getItemWiseSaleHistory();
    	
    	String[] header = new String[] { "ID", "Item Name", "UOM","Rate","Quantity","Amount","Sale Date","Invoice Name"};
    	String type="pdf";
    	
    	reportGenerateContext.setReportGenerateStrategy(new ItemWiseSaleHistoryReportStrategy());
    	
    	ByteArrayInputStream bis = reportGenerateContext.buildReport(header, type, itemWiseSaleHistoryDTO_list);
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=item_wise_sale_history_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    @GetMapping(value = "/reports/itemwisesalehistory/excel",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<InputStreamResource> invoiceSalesHistoryExcelReport() throws IOException, ResourceNotFoundException {
    
	   // ByteArrayInputStream in = ExcelGenerator.item_wise_sale_history_Excel(itemWiseSaleHistoryDTO_list);
	    // return IOUtils.toByteArray(in);
	    
	    
		List<ItemWiseSaleHistoryDTO> itemWiseSaleHistoryDTO_list = itemWiseSaleHistoryService.getItemWiseSaleHistory();
    	
    	String[] header = new String[] { "ID", "Item Name", "UOM","Rate","Quantity","Amount","Sale Date","Invoice Name"};
    	String type="pdf";
    	
    	reportGenerateContext.setReportGenerateStrategy(new ItemWiseSaleHistoryReportStrategy());
    	
    	ByteArrayInputStream in = reportGenerateContext.buildReport(header, type, itemWiseSaleHistoryDTO_list);
	    
	    HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=saleshistoryinvoicereport.xlsx");
	    
	     return ResponseEntity
	                  .ok()
	                  .headers(headers)
	                  .body(new InputStreamResource(in));
	     }
	
}
