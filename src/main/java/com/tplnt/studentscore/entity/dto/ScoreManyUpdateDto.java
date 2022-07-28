package com.tplnt.studentscore.entity.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class ScoreManyUpdateDto {

	@Min(value = 1L, message ="{score.studentId.min}")
	@NotNull(message ="{score.studentId.notNull}")
	private Long studentId;
	
	@NotEmpty(message = "{score.subjectScores.notEmpty}")
	@Valid
	private List<SubjectScoreUpdateDto> subjectScores;

}
