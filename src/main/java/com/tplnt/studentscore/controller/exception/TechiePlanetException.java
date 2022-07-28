package com.tplnt.studentscore.controller.exception;

public class TechiePlanetException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private Integer code = 500;
		
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}

}
