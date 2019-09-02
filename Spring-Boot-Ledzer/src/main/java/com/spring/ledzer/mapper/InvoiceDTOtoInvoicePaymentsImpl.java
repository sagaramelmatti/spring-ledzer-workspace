package com.spring.ledzer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Account;
import com.spring.ledzer.model.Customer;
import com.spring.ledzer.model.Invoice;
import com.spring.ledzer.model.InvoicePayments;
import com.spring.ledzer.model.PaymentMode;
import com.spring.ledzer.model.dto.InvoiceDTO;
import com.spring.ledzer.repository.InvoicePaymentsRepository;


@Component
public class InvoiceDTOtoInvoicePaymentsImpl implements InvoiceDTOtoInvoicePayments {

	@Autowired
	InvoicePaymentsRepository   invoicePaymentsRepository;
	
	InvoicePayments   invoicePayments = null;
	
	@Override
	public InvoicePayments setInvoicePayments(InvoiceDTO invoiceDTO, Invoice invoice) {
		
		try
		{
			invoicePayments = invoicePaymentsRepository.findByInvoiceId(invoice.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
    	if(invoicePayments == null)
    	{
    		invoicePayments = new InvoicePayments();
    		Long payment_number = invoicePaymentsRepository.getNextPaymentNumber();
    		invoicePayments.setPaymentNumber(payment_number);
	    	invoicePayments.setPaymentVoucher("INVPYMT"+ payment_number);
    	}
    	
    	Account account = new Account();
    	account.setId(invoiceDTO.getAccountId());
    	invoicePayments.setAccount(account);
    	
    	PaymentMode paymentMode = new PaymentMode();
    	paymentMode.setId(invoiceDTO.getPaymentModeId());
    	invoicePayments.setPaymentMode(paymentMode);
    	
    	invoicePayments.setChequeNo(invoiceDTO.getChequeNo());
    	invoicePayments.setBankName(invoiceDTO.getBankName());
    	
    	
    	invoicePayments.setInvTotalAmt(invoiceDTO.getInvoiceAmt());
    	invoicePayments.setAmountPaid(invoiceDTO.getAmountPaid());
    	invoicePayments.setAmountDue(invoiceDTO.getAmountDue());
    	invoicePayments.setPaymentDate(invoiceDTO.getInvoiceDate());
    	
    	Customer customer_new = new Customer();
    	customer_new.setId(invoiceDTO.getCustomerId());
    	invoicePayments.setCustomer(customer_new);
    	
    	invoicePayments.setInvoice(invoice);
    	
    	//invoice.setInvoicePayments(invoicePayments);
		
		return invoicePayments;
	}
	
	

}
