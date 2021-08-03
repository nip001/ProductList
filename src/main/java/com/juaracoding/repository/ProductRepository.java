package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value = "Select * from product where product_name LIKE %?1%",nativeQuery = true)
	List<Product> findProductByName(String name);
}
