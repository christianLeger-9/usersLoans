package com.usersLoans.usersLoans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.usersLoans.usersLoans.controllers.UserController;
import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.service.UserService;

@SpringBootTest
public class UserControllerTests {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testSaveUser() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Users u = new Users();
		u.setEmail("a@a.com");
		u.setFirstName("leger");
		u.setLastName("christian");
		u.setLoans(new ArrayList<>());
		ResponseEntity<Users> responseEntity = (ResponseEntity<Users>) userController.saveUser(u, null);
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testDeleteUser() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Users u = new Users();
		u.setId(new Long(2));
		ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) userController.deleteUser(u.getId(), null);
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	@Test
	void testFindUser() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Users u = new Users();
		u.setId(new Long(2));
		ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) userController.users(u.getId(), null);
//		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.OK.value());
		assertTrue(responseEntity.getStatusCodeValue() == HttpStatus.NOT_FOUND.value());
	}
}