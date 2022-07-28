package com.tplnt.studentscore.entity.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class SubjectScoreUpdateDto {
	
	@Min(value = 1L, message ="{score.scoreId.min}")
	private Long scoreId;
	
	@Min(value = 1L, message ="{score.subjectId.min}")
	@NotNull(message ="{score.subjectId.notNull}")
	private Long subjectId;
	
	@Min(value = 0, message = "{score.overallMark.min}")
	@Max(value = 100, message = "{score.overallMark.max}")
	@NotNull(message ="{score.overallMark.notNull}")
	private Integer overallMark;

}
