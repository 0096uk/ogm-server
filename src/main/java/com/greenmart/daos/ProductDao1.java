package com.greenmart.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmart.pojos.Product;

public interface ProductDao1 extends JpaRepository<Product, Integer> {

}
