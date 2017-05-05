package com.example.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.ProductCategory;

public interface ProductCategoryDAO extends CrudRepository<ProductCategory, Long>{

}