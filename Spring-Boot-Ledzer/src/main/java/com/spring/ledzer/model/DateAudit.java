package com.spring.ledzer.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt", "createdBy"},
        allowGetters = true
)
public abstract class DateAudit<U> {
 
		@CreatedBy
    	@Column(name = "created_by")
		private U createdBy;
	
    	@CreatedDate
    	@Column(nullable = false, updatable = false)
    	private Instant createdAt;
 
    	@LastModifiedDate
    	@Column(nullable = false)
    	private Instant updatedAt;
 
    public Instant getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
 
    public Instant getUpdatedAt() {
        return updatedAt;
    }
 
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}
}
