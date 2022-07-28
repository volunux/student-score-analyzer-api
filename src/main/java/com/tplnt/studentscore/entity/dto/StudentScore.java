package com.tplnt.studentscore.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StudentScore extends BaseStudentScore {
	
	private Double medianScore;
	
	private Integer modeScore;

}
