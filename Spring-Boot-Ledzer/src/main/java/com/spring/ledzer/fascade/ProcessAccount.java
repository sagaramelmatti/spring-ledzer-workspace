package com.spring.ledzer.fascade;

import java.math.BigDecimal;

public interface ProcessAccount {
	
	public int updateAccountDetails(Long accountId, BigDecimal invoiceAmt, String action);

}
