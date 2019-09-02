package com.spring.ledzer.fascade;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.InvoicePayments;
import com.spring.ledzer.service.InvoiceService;

@Component
public class ProcessInvoiceImpl implements ProcessInvoice {
	
	@Autowired
	InvoiceService invoiceService;

	@Override
	public Invoice createNewInvoice(Invoice invoice, Set<InvoiceDetail> incoice_detail_set, InvoicePayments invoice_Payments) {
		
		Invoice invoice_result = null;
		invoice.setInvoicePayments(invoice_Payments);
		invoice.setInvoiceDetails(incoice_detail_set);
		invoice_result = invoiceService.save(invoice);
		
		return invoice_result;
		
	}

}
