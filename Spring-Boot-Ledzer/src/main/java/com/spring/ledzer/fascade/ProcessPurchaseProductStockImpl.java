package com.spring.ledzer.fascade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;
import com.spring.ledzer.service.ProductService;

@Component
public class ProcessPurchaseProductStockImpl implements ProcessPurchasePrductStock {
	
	@Autowired
	ProductService productService;

	@Override
	public int updateStock(PurchaseDetail purchaseDetail, PurchaseDetailDTO purchaseDetailDTO) {
		
		int result =0; 
			if(purchaseDetailDTO.getPreProductId() != null && purchaseDetailDTO.getPreProductId() != 0  )
			{
				result = productService.updateProductStock(purchaseDetailDTO.getPreProductId(), purchaseDetailDTO.getPreQuantity(),"subtract");
			}
			result = productService.updateProductStock(purchaseDetail.getProduct().getId(), purchaseDetail.getQuantity(),"add");
		return result;
	}
}
