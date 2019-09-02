package com.spring.ledzer.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Account;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("SELECT a.id FROM Account a where a.defaultAccSts = 'Y'   ")
	Long getDefaultAccount();

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Account SET balance = :new_balance WHERE id = :accountId")
	int setAccountBalance(@Param("accountId") Long accountId,@Param("new_balance") BigDecimal new_balance );

	@Query("SELECT a.balance FROM Account a where a.id = :accountId   ")
	BigDecimal getAccountBalance(@Param("accountId") Long accountId);
}
