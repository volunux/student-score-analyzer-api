package com.tplnt.studentscore.entity.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class StudentSubjectScoreToUpdate {

	private Long studentId;
	private List<SubjectScoreToUpdateDto> subjectScoreToUpdateDtos;

}
