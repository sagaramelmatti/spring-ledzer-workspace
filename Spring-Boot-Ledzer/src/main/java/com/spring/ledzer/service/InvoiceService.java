package com.spring.ledzer.service;

import java.util.List;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

public interface InvoiceService {
	
	public List<InvoiceDTO> getAllInvoices();
	
	public Long getNextInvoiceId();

	public List<InvoiceDetailDTO> getAllInvoiceDetails(Long invoiceId);
	
	public Invoice save(Invoice invoice);
	
	public InvoiceDTO getInvoice(Long invoiceId) throws ResourceNotFoundException;

	public InvoiceDetail updateInvoiceDetail(InvoiceDetailDTO invoiceDetailDTO, Invoice invoice);

	//public Invoice updateInvoiceDetailEntry(long id, InvDTO invoiceDTO);

	public InvoiceDetailDTO getInvoiceDetail(Long id) throws ResourceNotFoundException;

	Invoice getInvoiceObject(Long id) throws ResourceNotFoundException;

}
