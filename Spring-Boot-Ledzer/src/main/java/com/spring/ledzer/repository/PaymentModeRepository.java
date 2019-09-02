package com.spring.ledzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.PaymentMode;

@RepositoryRestResource
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {
}
