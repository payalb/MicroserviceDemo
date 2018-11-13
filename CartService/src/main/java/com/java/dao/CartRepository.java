package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.java.dto.Cart;
import com.java.dto.Product;
@RepositoryRestResource(path="doctors")
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
		public void addProductToCart(Product product);
}
