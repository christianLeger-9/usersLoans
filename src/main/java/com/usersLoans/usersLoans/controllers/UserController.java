package com.usersLoans.usersLoans.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userLoans.userLoans.constants.Constants;
import com.usersLoans.usersLoans.data.Loans;
import com.usersLoans.usersLoans.data.Users;
import com.usersLoans.usersLoans.repository.LoanRepository;
import com.usersLoans.usersLoans.service.LogService;
import com.usersLoans.usersLoans.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	LoanRepository loanRepository;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	//Devuelve el usuario enviado por parametro
	public ResponseEntity<?> users(@PathVariable Long id, HttpServletRequest request){
		Optional<Users> user = null;
		ArrayList<Loans> loans = null;
		boolean ok = true;
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			user = userService.findById(id);
			if (user.isPresent()) {
				loans = (ArrayList<Loans>) loanRepository.findByIdUser(user.get().getId());
				user.get().setLoans(loans);
			} else {
				ok = false;
				logService.saveLog(id, ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, String.format("Error al buscar un usuario por id, usuario inexistente "), request.getLocalName());
				throw new Exception("Usuario inexistente");
			}
		}
		catch( Exception e) { 
			if (!ok) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body((e.getMessage()));
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
			}
		 }
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	//agrega un nuevo usuario cargado previamente
	public ResponseEntity<?> saveUser(@RequestBody Users user, HttpServletRequest request) {
		Users userDto = new Users();
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			userDto = userService.saveUser(user);
		} catch( Exception e) { 
			logService.saveLog(user.getId(), ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, String.format("Error al guardar usuario"), request.getLocalName());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((e.getMessage()));
		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpServletRequest request){
		boolean ok = false ;
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			ok = userService.deleteUser(id);
			if(!ok) {
				throw new Exception("Error al eliminar un usuario");
			}
		} catch( Exception e) { 
			logService.saveLog(id, ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, String.format("Error al eliminar usuario"), request.getLocalName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		}
		return new ResponseEntity<>(ok,HttpStatus.OK);
	}
}
