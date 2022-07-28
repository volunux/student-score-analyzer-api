package com.tplnt.studentscore.entity.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SubjectScoreToUpdateDto {

	private Long scoreId;
	private String subjectTitle;
	private String subjectCode;
	private Long subjectId;
	private Integer overallMark;
	
	public SubjectScoreToUpdateDto() {
		overallMark = 0;
	}
}
