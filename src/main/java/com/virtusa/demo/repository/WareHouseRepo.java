package com.virtusa.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.virtusa.demo.bean.WareHouse;

public interface WareHouseRepo extends CrudRepository<WareHouse, Long>{
	@Transactional
	@Modifying
	@Query("update WareHouse wh set wh.count= wh.count + ?2 where wh.id = ?1")
	void updateCount(long id, long count);
	
	@Transactional
	@Modifying
	@Query("update WareHouse wh set wh.count= wh.count - ?2 where wh.id = ?1")
	void moveToShelf(long id, long count);
}
