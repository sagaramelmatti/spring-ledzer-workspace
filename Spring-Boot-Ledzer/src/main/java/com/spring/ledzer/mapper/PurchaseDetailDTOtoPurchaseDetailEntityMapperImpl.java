package com.spring.ledzer.mapper;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.model.Product;
import com.spring.ledzer.model.Purchase;
import com.spring.ledzer.model.PurchaseDetail;
import com.spring.ledzer.model.Uom;
import com.spring.ledzer.model.dto.PurchaseDetailDTO;
import com.spring.ledzer.repository.PurchaseDetailRepository;

@Component
public class PurchaseDetailDTOtoPurchaseDetailEntityMapperImpl implements PurchaseDetailDTOtoPurchaseDetailEntityMapper {

	@Autowired
	PurchaseDetailRepository purchaseDetailRepository;
	
	@Override
	public PurchaseDetail convertDtoToEntityMapper(Purchase purchase,PurchaseDetailDTO purchaseDetailDTO) {
		
		PurchaseDetail purchaseDetail = new PurchaseDetail();
		Optional<PurchaseDetail> purchase_details_Data = null;
		
			if(purchaseDetailDTO.getId() != null && purchaseDetailDTO.getId() != 0)
			{
				purchase_details_Data = purchaseDetailRepository.findById(purchaseDetailDTO.getId());
				if (purchase_details_Data.isPresent()) {
					purchaseDetail = purchase_details_Data.get();
				}
			}
			else
			{
				purchaseDetail = new PurchaseDetail();
				purchaseDetail.setPurchase(purchase);
			}
			
			purchaseDetail.setTotalPrice(purchaseDetailDTO.getTotalPrice());
			purchaseDetail.setCgstAmt(purchaseDetailDTO.getCgstAmt());
			purchaseDetail.setCgstPer(purchaseDetailDTO.getCgstPer());
			purchaseDetail.setDiscAmt(purchaseDetailDTO.getDiscAmt());
			purchaseDetail.setDiscPer(purchaseDetailDTO.getDiscPer());
			purchaseDetail.setHsnCode(purchaseDetailDTO.getHsnCode());
			purchaseDetail.setIgstAmt(purchaseDetailDTO.getIgstAmt());
			purchaseDetail.setIgstPer(purchaseDetailDTO.getIgstPer());
			purchaseDetail.setQuantity(purchaseDetailDTO.getQuantity());
			purchaseDetail.setRate(purchaseDetailDTO.getRate());
			purchaseDetail.setRemStock(purchaseDetailDTO.getRemStock());
			purchaseDetail.setSgstAmt(purchaseDetailDTO.getSgstAmt());
			purchaseDetail.setSgstPer(purchaseDetailDTO.getSgstPer());
			purchaseDetail.setTaxableAmt(purchaseDetailDTO.getTaxableAmt());
			purchaseDetail.setRemStock(new BigDecimal(0.00));
			
			Uom uom = new Uom();
			uom.setId(purchaseDetailDTO.getUomId());
			purchaseDetail.setUom(uom);
			
			Product product = new Product();
			product.setId(purchaseDetailDTO.getProductId());
			//product.setStock(product.getStock().subtract(purchaseDetail.getQuantity()));
			purchaseDetail.setProduct(product);
		
		return purchaseDetail;
		
	}

}
