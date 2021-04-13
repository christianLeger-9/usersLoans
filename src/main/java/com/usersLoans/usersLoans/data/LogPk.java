package com.usersLoans.usersLoans.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class LogPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="USUARIO")
	private Long usuario;
	@Column(name="SUBSISTEMA", length=20)
	private String subSistema;
	@Column(name="IP", length=15)
	private String ip;
	@Column(name="HOSTNAME", length=50)
	private String hostname;
	@Column(name="FECHA", length=50)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Column(name="DESCRIPCION", length=1000)
	private String descripcion;
	@Column(name="ACCION", length=20)
	private String accion;
	
	
	public LogPk() {
		super();
	}
	public LogPk(Long usuario, String subSistema, String ip, String hostname, Date fecha, String descripcion,
			String accion) {
		super();
		this.usuario = usuario;
		this.subSistema = subSistema;
		this.ip = ip;
		this.hostname = hostname;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.accion = accion;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public String getSubSistema() {
		return subSistema;
	}
	public void setSubSistema(String subSistema) {
		this.subSistema = subSistema;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accion == null) ? 0 : accion.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((subSistema == null) ? 0 : subSistema.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogPk other = (LogPk) obj;
		if (accion == null) {
			if (other.accion != null)
				return false;
		} else if (!accion.equals(other.accion))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (subSistema == null) {
			if (other.subSistema != null)
				return false;
		} else if (!subSistema.equals(other.subSistema))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}