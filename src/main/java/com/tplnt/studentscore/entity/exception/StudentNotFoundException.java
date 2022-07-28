package com.tplnt.studentscore.entity.exception;

import com.tplnt.studentscore.controller.exception.TechiePlanetException;

public class StudentNotFoundException extends TechiePlanetException {

	private static final long serialVersionUID = 1L;
	public static String entityName = "Student";
	private Object entityId = null;
	
	public StudentNotFoundException(Object entityId) {
		this.entityId = entityId;
	}	
	
	@Override
	public String getMessage() {
		return String.format("%s with an id %s cannot be found or does not exist in record.", entityName, entityId.toString());
	}

}
