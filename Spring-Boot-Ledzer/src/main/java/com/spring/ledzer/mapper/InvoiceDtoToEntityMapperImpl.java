package com.spring.ledzer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.Customer;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.repository.InvoiceRepository;

@Component
public class InvoiceDtoToEntityMapperImpl implements InvoiceDtoToEntityMapper {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public Invoice setInvoiceDTOtoInvoiceEnity(InvoiceDTO invoiceDTO, long invoiceId) throws ResourceNotFoundException {
		
		Invoice _invoice = null;
		
		if(invoiceId != -1)
		{
			_invoice = invoiceRepository.findById(invoiceId).orElseThrow(
	                () -> new ResourceNotFoundException("_invoice", "id",invoiceId));
		}
		else
		{
			_invoice = new Invoice();
		}
		
		_invoice.setInvoiceNo(invoiceDTO.getInvoiceNo());
		_invoice.setName(invoiceDTO.getName());
		if (invoiceDTO.getCustomerId() != null) {
	    	
    		Customer customer = new Customer();
	    	customer.setId(invoiceDTO.getCustomerId());
	    	_invoice.setCustomer(customer);
	    }
		
		_invoice.setDiscountAmt(invoiceDTO.getDiscountAmt());
		_invoice.setId(invoiceDTO.getId());
		_invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
		_invoice.setInvoiceNo(invoiceDTO.getInvoiceNo());
		_invoice.setName(invoiceDTO.getName());
	    _invoice.setRoundAmt(invoiceDTO.getRoundAmt());
	    _invoice.setTaxAmt(invoiceDTO.getTaxAmt());
	    
	    _invoice.setBillAmt(invoiceDTO.getBillAmt());
		_invoice.setInvoiceAmt(invoiceDTO.getInvoiceAmt());
		_invoice.setAmountDue(invoiceDTO.getAmountDue());
		_invoice.setAmountPaid(invoiceDTO.getAmountPaid());
		
		return _invoice;
		
	}

}
