package com.usersLoans.usersLoans.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersLoans.usersLoans.data.Loans;
import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.repository.LoanRepository;
import com.usersLoans.usersLoans.repository.UserRepository;

@Service
public class LoanService {

	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Iterable<Loans> getLoansWithUser(Long idUser, Long page, Long size){
		return loanRepository.findByPageAndSizeAndIdUser(page,size,idUser);
	}
	
	public Iterable<Loans> getLoansWithOutUser(Long idUser, Long page, Long size){
		if (idUser != null) {
			return loanRepository.findByPageAndSizeAndIdUser(page,size,idUser);
		} else {
			return loanRepository.findByPageAndSize(page, size);
		}
		
	}
	
	public Loans saveLoans(Loans loans) {
		Optional<Users> usu= userRepository.findById(loans.getIdUser());
		if (usu.isPresent()) {
			return loanRepository.save(loans);
		} else {
			return null;
		}
	}
	
	public Optional<Loans> findById(Long id){
		return loanRepository.findById(id);
	}
}
