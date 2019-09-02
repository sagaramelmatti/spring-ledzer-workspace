package com.spring.ledzer.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ledzer.model.Tax;

@RepositoryRestResource
public interface TaxRepository extends JpaRepository<Tax, Long> {
}
