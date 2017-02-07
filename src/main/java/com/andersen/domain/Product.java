package com.andersen.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@Column
	@GeneratedValue(generator="product_increment")
	@GenericGenerator(name="product_increment", strategy = "increment")
	private int id;
	
	@Column(unique = true, nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private int productPrice;
	
	// Need for Hibernate.
	public Product(){
		
	}
	
	public Product(String name, int prise){
		this.productName = name;
		this.productPrice = prise;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(int produtPrice) {
		this.productPrice = produtPrice;
	}

}
