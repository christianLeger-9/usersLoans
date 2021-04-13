package com.usersLoans.usersLoans.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersLoans.usersLoans.data.LogPk;
import com.usersLoans.usersLoans.repository.LogRepository;


@Service
public class LogService {

	@Autowired 
	private LogRepository logRepository;

	@Transactional
	public void saveLog(Long usuario, String ip, Date fecha, String accion, String subsistema, String descripcion, String hostname) {
		LogPk log = new LogPk(usuario, subsistema, ip, hostname, fecha, accion, descripcion);
		logRepository.save(log);
	}
}