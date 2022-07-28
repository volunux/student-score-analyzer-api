package com.tplnt.studentscore.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "admission_number", nullable = false, updatable = false)
	private String admissionNumber;
	
	@Column(name ="first_name", nullable = false)
	private String firstName;
	
	@Column(name ="last_name", nullable = false)
	private String lastName;
	
	@Column(name ="email_address", nullable = false)
	private String emailAddress;
	
	@Column(name ="home_address", nullable = false)
	private String homeAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="created_on", updatable = false)
	private Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Date updatedOn;
	
	public Student() {
		this.admissionNumber = UUID.randomUUID().toString();
		this.createdOn = new Date();
		this.updatedOn = new Date();
	}
	
}
