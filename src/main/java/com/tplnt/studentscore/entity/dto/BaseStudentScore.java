package com.tplnt.studentscore.entity.dto;

import java.util.List;

import com.tplnt.studentscore.entity.Student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public abstract class BaseStudentScore {

	private Student student;
	
	private Integer totalScore;
	
	private List<SubjectScore> subjectsScore;
	
	private Double meanScore;

}
