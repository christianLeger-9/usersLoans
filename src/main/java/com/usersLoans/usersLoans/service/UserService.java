package com.usersLoans.usersLoans.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersLoans.usersLoans.data.Loans;
import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.repository.LoanRepository;
import com.usersLoans.usersLoans.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoanRepository loanRepository;
	
	public Optional<Users> findById(Long id){
		return userRepository.findById(id);
	}
	
	public Users saveUser(Users user){
		return userRepository.save(user);
	}
	
	public boolean deleteUser(Long id){
		Iterable<Loans> loans = null;
		try {
			userRepository.deleteById(id);
			loans = loanRepository.findByIdUser(id);
			Iterator<Loans> ite = loans.iterator();
			while(ite.hasNext()) {
				Loans loa = ite.next();
				loanRepository.deleteById(loa.getId());
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
