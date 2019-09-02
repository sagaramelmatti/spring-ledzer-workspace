package com.spring.ledzer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Supplier;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
