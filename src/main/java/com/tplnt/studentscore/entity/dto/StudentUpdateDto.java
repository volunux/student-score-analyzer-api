package com.tplnt.studentscore.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class StudentUpdateDto {

	@Size(max = 20, message ="{student.firstName.size}")
	private String firstName;

	@Size(max = 20, message = "{student.lastName.size}")
	private String lastName;
	
	@Email(message = "{student.emailAddress.pattern}")
	@Size(max = 50, message ="{student.emailAddress.size}")
	private String emailAddress;

	@Size(min = 1, max = 300, message ="{student.homeAddress.size}")
	private String homeAddress;
}
