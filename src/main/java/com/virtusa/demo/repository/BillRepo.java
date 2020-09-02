package com.virtusa.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.virtusa.demo.bean.Bill;

public interface BillRepo extends CrudRepository<Bill, Long>{

}
