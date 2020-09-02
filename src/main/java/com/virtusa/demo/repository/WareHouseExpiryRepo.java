package com.virtusa.demo.repository;

import java.util.List;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.virtusa.demo.bean.WareHouseExpiry;

public interface WareHouseExpiryRepo extends CrudRepository<WareHouseExpiry, Long> {
	
	@Query("from WareHouseExpiry where productId = ?1 and expireDate = DATE(?2)")
	WareHouseExpiry find(long productId, String expireDate);
	
	@Transactional
	@Modifying
	@Query("delete from WareHouseExpiry wh where wh.productId = ?1 and wh.expireDate = DATE(?2)")
	void delete(long productId, String strDate);
	
	@Transactional
	@Modifying
	@Query("update WareHouseExpiry wh set wh.count= wh.count + ?3 where wh.productId = ?1 and wh.expireDate = DATE(?2)")
	void updateCount(long productId, String strDate, long count);
	
	@Transactional
	@Modifying
	@Query("update WareHouseExpiry wh set wh.count= wh.count - ?3 where wh.productId = ?1 and wh.expireDate = DATE(?2)")
	void moveToShelf(long productId, String strDate, long count);

	@Query("from WareHouseExpiry order by productId")
	List<WareHouseExpiry> findAll();
}
