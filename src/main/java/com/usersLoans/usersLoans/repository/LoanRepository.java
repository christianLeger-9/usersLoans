package com.usersLoans.usersLoans.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.usersLoans.usersLoans.data.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	public abstract Iterable<Loans> findByPageAndSizeAndIdUser(Long idUser,Long page, Long size );
	public abstract Iterable<Loans> findByPageAndSize(Long page, Long size );
	public abstract Iterable<Loans> findByIdUser(Long idUser );
	
}
