package com.xseillier.springsocial.repository;

/**
 * @author xseillier
 * @version 1.0
 */

import org.springframework.data.repository.CrudRepository;

import com.xseillier.springsocial.repository.dto.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByEmail(String aUserName);
}
