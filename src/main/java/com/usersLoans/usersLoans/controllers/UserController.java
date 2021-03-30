package com.usersLoans.usersLoans.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.service.UserService;

@RestController
@RequestMapping(path="/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	//Devuelve el usuario enviado por parametro
	public ResponseEntity<?> users(@PathVariable Long id){
		Optional<Users> user = null;
		try {
			user = userService.findById(id);
		}
		catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		 }
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	//agrega un nuevo usuario cargado previamente
	public ResponseEntity<?> saveUser(@RequestBody Users user){
		Users userDto = new Users();
		try {
			userDto = userService.saveUser(user);
		} catch( Exception e) { 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		try {
			userService.deleteUser(id);
		} catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
