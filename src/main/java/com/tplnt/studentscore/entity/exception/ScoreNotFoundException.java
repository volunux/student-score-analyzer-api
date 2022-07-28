package com.tplnt.studentscore.entity.exception;

import com.tplnt.studentscore.controller.exception.TechiePlanetException;

public class ScoreNotFoundException extends TechiePlanetException {

	private static final long serialVersionUID = 1L;
	public static String entityName = "Score";
	private Object entityId = null;
	
	public ScoreNotFoundException(Object entityId) {
		this.entityId = entityId;
	}	
	
	@Override
	public String getMessage() {
		return String.format("%s with an id %s cannot be found or does not exist in record.", entityName, entityId.toString());
	}

}
