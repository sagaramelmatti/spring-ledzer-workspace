package com.spring.ledzer.fascade;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ledzer.repository.AccountRepository;

@Component
public class ProcessAccountImpl implements ProcessAccount {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	@Transactional
	public int updateAccountDetails(Long accountId, BigDecimal invoiceAmt, String action) {
		
		int result =0;
		BigDecimal account_balance = accountRepository.getAccountBalance(accountId);
		BigDecimal new_balance = null;
		
		if(action.equals("purchase"))
		{
			new_balance = account_balance.subtract(invoiceAmt);
		}
		else if(action.equals("invoice"))
		{
			new_balance = account_balance.add(invoiceAmt);
		}
			
		result =  accountRepository.setAccountBalance(accountId, new_balance);
		return result;
		
		
	}

}
