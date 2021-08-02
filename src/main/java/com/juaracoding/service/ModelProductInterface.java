package com.juaracoding.service;

import java.util.List;

import com.juaracoding.entity.Product;

public interface ModelProductInterface {

	public List<Product> getAllProduct();
	public String addProduct(Product product);
//	public String updateProduct(Product product);
//	public Product getByIdProduct(String id);
//	public String deleteProduct(String id);
}
