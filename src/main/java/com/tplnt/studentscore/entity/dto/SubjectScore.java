package com.tplnt.studentscore.entity.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SubjectScore {

	private String subjectTitle;
	private String subjectCode;
	private String subjectYear;
	private Integer score;
	
}
