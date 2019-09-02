package com.spring.ledzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.BusinessDetail;

@RepositoryRestResource
public interface BusinessRepository extends JpaRepository<BusinessDetail, Long> {
}
