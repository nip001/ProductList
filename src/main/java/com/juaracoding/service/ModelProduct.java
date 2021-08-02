package com.juaracoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.Product;
import com.juaracoding.repository.ProductRepository;

@Service
public class ModelProduct implements ModelProductInterface{

	@Autowired
	ProductRepository prodRepo;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return this.prodRepo.findAll();
	}

	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		this.prodRepo.save(product);
		return "Berhasil menambahkan product";
	}

}
