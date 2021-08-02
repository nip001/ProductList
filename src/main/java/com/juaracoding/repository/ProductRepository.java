package com.juaracoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juaracoding.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
