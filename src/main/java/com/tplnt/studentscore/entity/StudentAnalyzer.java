package com.tplnt.studentscore.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StudentAnalyzer {

	private String title = "Student Score Analyzer";
	private String message = "Welcome to Student Score Analyzer Web App Api";
	private String description = "A Spring Boot App Api that analyze and calculate the result of a students in subjects with the mean, median and mode and score";
	private String version = "1.0";
}
