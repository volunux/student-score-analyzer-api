package com.tplnt.studentscore.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StudentDto {

	@NotBlank(message = "{student.firstName.notBlank}")
	@Size(min = 1, max = 20, message = "{student.firstName.size}")
	private String firstName;
	
	@NotBlank(message = "{student.lastName.notBlank}")
	@Size(min = 1, max = 20, message = "{student.lastName.size}")
	private String lastName;
	
	@NotEmpty(message ="{student.emailAddress.notBlank}")
	@Email(message = "{student.emailAddress.pattern}")
	@Size(min = 1, max = 50, message ="{student.emailAddress.size}")
	private String emailAddress;
	
	@NotEmpty(message ="{student.homeAddress.notBlank}")
	@Size(min = 1, max = 300, message ="{student.homeAddress.size}")
	private String homeAddress;
}
