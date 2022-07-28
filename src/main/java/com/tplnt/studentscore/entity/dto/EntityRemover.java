package com.tplnt.studentscore.entity.dto;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EntityRemover {

	@NotEmpty(message ="{entityRemover.ids.notEmpty}")
	private Long[] ids;
}
