package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id  // 以@Id指定一個欄位為資料表對應的主鍵(primary key)
	@GeneratedValue(strategy = GenerationType.AUTO)  // 如果這個欄位的內容是自動產生，則必須加上
	private Long id;
	private String name;
	private int price;
	
	private int amount;
	private int safeamount;
	private String color;
	private String model;
	private String company;
	
	 // JoinColumn refers to the column name in this table (book)
	 @ManyToOne
	 @JoinColumn(name = "category")
	 private ProductCategory productCategory;
	 
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
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;

	}

	public int getSafeamount() {
		return safeamount;
	}

	public void setSafeamount(int safeamount) {
		this.safeamount = safeamount;
	}
	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	 public ProductCategory getProductCategory() {
		  return productCategory;
		 }

		 public void setProductCategory(ProductCategory productCategory) {
		  this.productCategory = productCategory;
		 }
}
