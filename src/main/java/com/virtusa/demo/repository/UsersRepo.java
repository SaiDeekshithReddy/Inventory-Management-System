package com.virtusa.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.virtusa.demo.bean.Users;

public interface UsersRepo extends CrudRepository<Users, Long> {
	
}
