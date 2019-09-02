package com.spring.ledzer.fascade;

import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;

public interface ProcessPrductStock {
	
	public int updateStock(InvoiceDetail invoiceDetailObj, InvoiceDetailDTO invoiceDetailDTO);

}
