package com.spring.ledzer.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ledzer.model.AccountGroup;

@RepositoryRestResource
public interface AccountgroupRepository extends JpaRepository<AccountGroup, Long> {
}
