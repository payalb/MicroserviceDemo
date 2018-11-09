package com.java.dao;

import org.springframework.data.repository.CrudRepository;

import com.java.dto.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

}
