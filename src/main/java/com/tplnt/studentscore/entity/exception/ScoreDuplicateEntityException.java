package com.tplnt.studentscore.entity.exception;

import com.tplnt.studentscore.controller.exception.TechiePlanetException;

public class ScoreDuplicateEntityException extends TechiePlanetException {

	private static final long serialVersionUID = 1L;
	public static String entityName = "Score";
	
	@Override
	public String getMessage() {
		return String.format("%s entry already exists in record", entityName);
	}
}
