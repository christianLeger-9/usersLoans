package com.usersLoans.usersLoans.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loans")
public class Loans implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name="total")
	private Double total;
	
	@NotNull
	@Column(name="idUser")
	private Long idUser;
	
	@NotNull
	@Column(name="page")
	private Long page;
	
	@NotNull
	@Column(name="size")
	private Long size;
	
	
}
