package com.spring.ledzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.fascade.InvoiceFascade;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;
import com.spring.ledzer.repository.InvoiceRepository;
import com.spring.ledzer.service.InvoiceService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	InvoiceRepository repository;
	
	@Autowired
	InvoiceFascade invoiceFascade;
	
	@GetMapping("/invoices")
	public List<InvoiceDTO> getAllInvoices() {
		List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return invoices;
	}
	
	@GetMapping("/invoices/list")
	public List<InvoiceDTO> getAllInvoicesList() {
		List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return invoices;
	}
	
	@GetMapping("/invoices/getMaxId")
	public Long getMaxInvoiceId() {
		Long invoices_id = invoiceService.getNextInvoiceId();
        return invoices_id;
	}
	
	@GetMapping("/invoices/invoiceDetails/{id}")
	public List<InvoiceDetailDTO> getAllInvoiceDetails(@PathVariable("id") Long id) 
	{
		List<InvoiceDetailDTO> invoiceDetails = invoiceService.getAllInvoiceDetails(id);
		
        return invoiceDetails;
	}
	
	@GetMapping("/invoices/{id}")
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable("id") Long id) throws ResourceNotFoundException {
		InvoiceDTO invoiceDTO = invoiceService.getInvoice(id);

		System.out.println("invoice date="+invoiceDTO.getInvoiceDate());
		return ResponseEntity.ok().body(invoiceDTO);
	}
	
	@PostMapping(value = "/invoices/create")
	public Invoice postInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		Invoice invoice = null;
		try
		{
			System.out.println("post invoice called");
			//invoiceFascade invoiceFascade = new SaveInvoiceDetailsFascadeImpl();
			long id = -1;
			invoice = invoiceFascade.saveInvoice(invoiceDTO,id);
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return invoice;
	}
	
	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody InvoiceDTO invoiceDTO) throws ResourceNotFoundException {
		System.out.println("Update Invoice with ID = " + id + "...");
		System.out.println("Update invoice called");
		//invoiceFascade invoiceFascade = new SaveInvoiceDetailsFascadeImpl();
		return new ResponseEntity<>(invoiceFascade.saveInvoice(invoiceDTO,id), HttpStatus.OK);
		
	}
	@PutMapping("/invoices/invoicedetails/{id}")
	public ResponseEntity<InvoiceDetail> updateInvoiceDetails(@PathVariable("id") long id, @RequestBody InvoiceDetailDTO invoiceDetailDTO) {
		System.out.println("Update InvoiceDetail with ID = " + id + "...");
		//return new ResponseEntity<>(invoiceService.updateInvoiceDetail(id,invoiceDetailDTO), HttpStatus.OK);
		return null;
		
	}
	
	@PutMapping("/invoices/updateinvoicedetail/{id}")
	public ResponseEntity<Invoice> updateInvoiceDetailEntry(@PathVariable("id") long id, @RequestBody InvoiceDTO invoiceDTO) throws ResourceNotFoundException {
		System.out.println("Update InvoiceDetail with ID = " + id + "...");
		//return new ResponseEntity<>(invoiceService.updateInvoiceDetailEntry(id,invoiceDTO), HttpStatus.OK);
		return new ResponseEntity<>(invoiceFascade.saveInvoice(invoiceDTO,id), HttpStatus.OK);
	}
	
	@GetMapping("/invoices/invoicedetail/{id}")
	public ResponseEntity<InvoiceDetailDTO> getInvoiceDetail(@PathVariable("id") Long invoiceDetailId) throws ResourceNotFoundException {
		InvoiceDetailDTO invoiceDetailDTO = invoiceService.getInvoiceDetail(invoiceDetailId);
		return ResponseEntity.ok().body(invoiceDetailDTO);
	}
	
}
