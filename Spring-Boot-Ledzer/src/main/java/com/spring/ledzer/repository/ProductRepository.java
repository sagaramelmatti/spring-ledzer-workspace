package com.spring.ledzer.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	@Query("SELECT p.stock  FROM Product p WHERE p.id = :productid")
	BigDecimal getProductStock(@Param("productid") Long productid);
	
	@Modifying(clearAutomatically = true)
    //@Query(value ="UPDATE product p SET p.stock = ( ( select c.stock from  product c where  c.id = :productid ) - :quantity ) WHERE p.id = :productid", nativeQuery = true)
	@Query("UPDATE Product p SET p.stock = :new_stock WHERE p.id = :productid")
    int updateProductStock(@Param("productid") Long productid,@Param("new_stock") BigDecimal new_stock);
}
