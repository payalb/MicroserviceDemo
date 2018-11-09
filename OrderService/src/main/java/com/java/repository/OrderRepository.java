package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.java.dto.Order;

@RepositoryRestResource(exported=false)
public interface OrderRepository extends JpaRepository<Order, Integer>{

	
}
