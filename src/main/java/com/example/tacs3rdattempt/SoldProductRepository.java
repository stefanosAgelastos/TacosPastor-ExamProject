package com.example.tacs3rdattempt;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SoldProductRepository extends CrudRepository <SoldProduct,Integer> {
    List<SoldProduct> findBySoldDateBetween(Date start, Date finish);

}
