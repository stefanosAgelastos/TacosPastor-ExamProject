package com.example.tacs3rdattempt;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Product, Integer >{
    Product findByProductName (String productName);
}
