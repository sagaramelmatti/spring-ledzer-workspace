package com.spring.ledzer.fascade;

import java.util.Set;

import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.InvoicePayments;

public interface ProcessInvoice {
	
	public Invoice createNewInvoice(Invoice invoice, Set<InvoiceDetail> invoiceDetails_set, InvoicePayments invoicePayments);

}
