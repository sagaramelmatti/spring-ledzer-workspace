package com.spring.ledzer.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uom")
public class Uom extends DateAudit<Integer> implements java.io.Serializable
{
	
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	private Set<Product> products = new HashSet<Product>(0);
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>(0);
	
	public Uom(String name) {
		super();
		this.name = name;
	}
	
	public Uom()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uom",cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uom",cascade = CascadeType.ALL)
	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
}
