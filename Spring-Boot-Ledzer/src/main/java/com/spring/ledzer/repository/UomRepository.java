package com.spring.ledzer.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.spring.ledzer.model.Uom;

import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource
public interface UomRepository extends JpaRepository<Uom, Long> {
}
