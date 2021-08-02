package com.juaracoding.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.entity.Product;
import com.juaracoding.service.ModelProduct;
import com.juaracoding.utility.FileUtility;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ModelProduct modProduct;
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.modProduct.getAllProduct());
	}
	
	@PostMapping("/add")
	public String addProduct(@RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON) throws IOException {
		String filename = StringUtils.cleanPath(images.getOriginalFilename());

		String uploadDir="user-photos/";
		FileUtility.saveFile(uploadDir, filename, images);
		Product product = new Gson().fromJson(dataJSON, Product.class);
		product.setProductImage(filename) ;
		
		
		return this.modProduct.addProduct(product);
		
	}
	
	
}
