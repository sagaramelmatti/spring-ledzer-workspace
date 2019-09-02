package com.spring.ledzer.fascade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.InvoiceDetail;
import com.spring.ledzer.model.dto.InvoiceDetailDTO;
import com.spring.ledzer.service.ProductService;

@Component
public class ProcessProductStockImpl implements ProcessPrductStock {
	
	@Autowired
	ProductService productService;

	@Override
	public int updateStock(InvoiceDetail invoiceDetailObj, InvoiceDetailDTO invoiceDetailDTO) {
		
		int result =0; 
		if(invoiceDetailDTO.getPreProductId() != null && invoiceDetailDTO.getPreProductId() != 0  )
		{
			result = productService.updateProductStock(invoiceDetailDTO.getPreProductId(), invoiceDetailDTO.getPreQuantity(),"add");
		}
		result = productService.updateProductStock(invoiceDetailObj.getProduct().getId(), invoiceDetailObj.getQuantity(),"subtract");
		return result;
	}

}
