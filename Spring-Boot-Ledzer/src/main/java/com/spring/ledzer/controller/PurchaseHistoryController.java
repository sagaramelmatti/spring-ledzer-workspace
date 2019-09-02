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
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.PurchaseHistoryDTO;
import com.spring.ledzer.repository.PurchaseHistoryRepository;
import com.spring.ledzer.service.PurchaseHistoryService;
import com.spring.ledzer.util.PDFGenerator;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PurchaseHistoryController {

	 @Autowired
	 PurchaseHistoryRepository purchaseSalesReportRepository;
	 
	 @Autowired
	 PurchaseHistoryService purchaseSaleHistoryService;
	 
	 
	 @GetMapping("/purchasehistory")
		public List<PurchaseHistoryDTO> getAllPurchases() throws ResourceNotFoundException {
			List<PurchaseHistoryDTO> purchase = purchaseSaleHistoryService.getPurchasesSaleHistory();
	        return purchase;
	}

    @GetMapping(value = "/purchasereports/purchasealeshistory",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> purchaseSalesHistoryReport() throws IOException 
    {
    	/*
        List<Invoice> purchase = (List<Invoice>) invoiceHistoryRepository.findAll();
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(purchase);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=saleshistorypurchasereport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
                
                */
    	return null;
    }
	
}
