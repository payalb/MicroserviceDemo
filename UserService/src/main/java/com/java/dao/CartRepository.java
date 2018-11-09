package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.Cart;
import com.java.dto.Product;
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
		public void addProductToCart(Product product);
		public void deleteProductFromCart(Product product);
		@Modifying
		@Query("update Product set quantity= :quantity where name =: name")
		public void updateProduct_Quantity(@Param("name")String name,@Param("quantity")int quantity);

}
