package com.example.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.ProductCategoryDAO;
import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.entity.ProductCategory;

@Controller
public class ProductController {
	@Autowired
	// 對應的Controller中利用@Autowired串連前面的ProductDAO
	ProductDAO dao;
	
	 @Autowired
	 ProductCategoryDAO categoryDao;
	 
	@RequestMapping(value = "/productCreate", method = RequestMethod.GET)
	public ModelAndView openFormCreate(Product pro) {
		ModelAndView model = new ModelAndView("productCreate");
		model.addObject(pro);
		Iterable<ProductCategory> categories = categoryDao.findAll();
		  model.addObject("allProductCategories", categories);
		return model;
	}

	@RequestMapping(value = "/productCreate", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@Valid @ModelAttribute Product pro,
			BindingResult bindingResult) throws SQLException {
		ModelAndView model;

		if (bindingResult.hasErrors()) {
			model = new ModelAndView("productCreate");
			return model;
		} else {
			model = new ModelAndView("redirect:/productRetrieveAll");

			dao.save(pro);

			model.addObject(pro);
			return model;
		}

	}

	@RequestMapping(value = { "/productRetrieveAll", "/" }, method = RequestMethod.GET)
	public ModelAndView retrieveProducts() throws SQLException {

		Iterable<Product> products = dao.findAll(); // CrudRepository內建了一個findAll的方法，讀取所有物件。
		// Iterable<Product> products = dao.findByOrderByWeightAsc();
		// Iterable<Product> products = dao.findByWeightGreaterThan(40);
		// Iterable<Product> products = dao.findOverWeight();
		// System.out.println(products.size());
		ModelAndView model = new ModelAndView("productList");
		model.addObject("allProducts", products);
		Iterable<ProductCategory> categories = categoryDao.findAll();
		  model.addObject("allProductCategories", categories);
		model.addObject("products", products);
		ProductCategory category = categories.iterator().next();//get first category
		  model.addObject("productCategory", category);
		return model;
	}
	 @RequestMapping(value = { "/productRetrieveByCategory" }, method = RequestMethod.POST)
	 public ModelAndView retrieveProductByCategory(
	   @RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
	  ModelAndView model = new ModelAndView("productList");
	  
	  Iterable<ProductCategory> categories = categoryDao.findAll();
	  model.addObject("allProductCategories", categories);
	  ProductCategory category = categoryDao.findOne(id);
	  model.addObject("productCategory", category);
	  
	  model.addObject("products", category.getproduct());
	  return model;
	 }

	@RequestMapping(value = "/productUpdate", method = RequestMethod.GET)
	public ModelAndView openFormUpdate(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("productUpdate");
		Product product = dao.findOne(id);
		model.addObject(product);
		Iterable<ProductCategory> categories = categoryDao.findAll();
		  model.addObject("allProductCategories", categories);
		return model;
	}

	@RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Product pro)
			throws SQLException {
		// ModelAndView model = new ModelAndView("productDone");
		ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");
		dao.save(pro);

		return model;
	}

	@RequestMapping(value = "/productDelete", method = RequestMethod.GET)
	// CrudRepository內建了一個delete的方法，刪除特定物件。delete會使用在Entity類別(如:Product)中@Id所指定的變數(如:id)作為刪除的依據
	public ModelAndView deleteProduct(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");

		dao.delete(id);

		return model;
	}

}
