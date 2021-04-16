package com.usersLoans.usersLoans.controllers;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userLoans.userLoans.constants.Constants;
import com.usersLoans.usersLoans.data.Loans;
import com.usersLoans.usersLoans.service.LoanService;
import com.usersLoans.usersLoans.service.LogService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value="/getLoansWithOutUser", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getLoansWithOutUser(HttpServletRequest request, @RequestParam(required=false,value="idUser") Long idUser, 
			@RequestParam(required=true,value="page") Long page, @RequestParam(required=true,value="size") Long size){
		Iterable<Loans> loans = null;
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			loans = loanService.getLoansWithOutUser(idUser,page,size);
			Iterator<Loans> ite = loans.iterator();
			if(!ite.hasNext()) {
				logService.saveLog(idUser, ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, 
						String.format("Error al buscar loans con usuario opcional"), request.getLocalName());
				throw new Exception("Error al buscar loans con usuario opcional");
			}
		}
		catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		 }
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getLoansWithUser", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getLoansWithUser(HttpServletRequest request, @RequestParam(required=true,value="idUser") Long idUser, 
			@RequestParam(required=true,value="page") Long page, @RequestParam(required=true,value="size") Long size){
		Iterable<Loans> loans = null;
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			loans = loanService.getLoansWithUser(idUser,page,size);
			Iterator<Loans> ite = loans.iterator();
			if(!ite.hasNext()) {
				logService.saveLog(idUser, ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, 
						String.format("Error al buscar loans con usuario obligatorio"), request.getLocalName());
				throw new Exception("Error al buscar loans con usuario obligatorio");
			}
		}
		catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((e.getMessage()));
		 }
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> saveLoans(@RequestBody Loans loans, HttpServletRequest request){
		Loans loanss = null;
		String ip = (request.getHeader("X-FORWARDED-FOR") == null) ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
		try {
			loanss = loanService.saveLoans(loans);
			if (loanss == null ) {
				logService.saveLog(null, ip, new Date(), Constants.LOG_ACCION_ERROR,  Constants.LOG_SUBSISTEMA, 
						String.format("El idUser no existe para poder agregarle loans"), request.getLocalName());
				throw new Exception("El idUser no existe para poder agregarle loans");
			}
		}
		catch( Exception e) { 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((e.getMessage()));
		 }
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}
	
}
