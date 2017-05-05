package com.example.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory {
  @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private String name;

  // mappedBy refers to the variable in Book
 @OneToMany(mappedBy = "productCategory")
 private List<Product> product;

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

  public Iterable<Product> getproduct() {
  return product;
 }


}