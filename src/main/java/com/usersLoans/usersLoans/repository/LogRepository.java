package com.usersLoans.usersLoans.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usersLoans.usersLoans.data.LogPk;

@Repository
public interface LogRepository extends CrudRepository<LogPk, Long>{
}