package com.virtusa.demo.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.virtusa.demo.bean.Shelf;

public interface ShelfRepo extends CrudRepository<Shelf, Long>{
	
	@Transactional
	@Modifying
	@Query("update Shelf sh set sh.count= sh.count + ?2, expireDate = ?3 where sh.id = ?1")
	void updateCount(long id, long count, Date expireDate);
	
	@Transactional
	@Modifying
	@Query("update Shelf sh set sh.count= sh.count - ?2 where sh.id = ?1")
	void reduceCount(long id, long count);
}
