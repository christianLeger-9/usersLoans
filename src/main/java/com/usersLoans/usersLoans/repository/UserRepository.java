package com.usersLoans.usersLoans.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usersLoans.usersLoans.data.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	
}
