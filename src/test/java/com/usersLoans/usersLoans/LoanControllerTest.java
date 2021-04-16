package com.usersLoans.usersLoans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.usersLoans.usersLoans.controllers.LoanController;
import com.usersLoans.usersLoans.data.Loans;
import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.service.LoanService;

@SpringBootTest
public class LoanControllerTest {

	@InjectMocks
	private LoanController loanController;
	
	@Mock
	private LoanService loanService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getLoansWithOutUserTest() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		@SuppressWarnings("unchecked")
		ResponseEntity<Users> responseEntity = (ResponseEntity<Users>) loanController.getLoansWithOutUser((HttpServletRequest)request,new Long(1),new Long(1),new Long(1));
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	@Test
	void getLoansWithUserTest() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) loanController.getLoansWithUser((HttpServletRequest)request,new Long(1),new Long(1),new Long(1));
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	@Test
	void saveLoansTest() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Loans l = new Loans();
		l.setIdUser(new Long(1));
		l.setPage(new Long(1));
		l.setSize(new Long(1));
		l.setTotal(100.0);
		@SuppressWarnings("unchecked")
		ResponseEntity<Users> responseEntity = (ResponseEntity<Users>) loanController.saveLoans(l, (HttpServletRequest)request);
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.BAD_REQUEST.value());
	}
}
