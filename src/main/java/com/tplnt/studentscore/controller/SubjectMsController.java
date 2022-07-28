package com.tplnt.studentscore.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.service.SubjectServiceMs;

@RestController
@RequestMapping(value = "subject-ms",
				consumes = { MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_JSON_VALUE } )
public class SubjectMsController {
	
	private SubjectServiceMs service;
	
	public SubjectMsController(SubjectServiceMs service) {
		this.service = service;
	}
	
	@GetMapping("/entries/{clientType}")
	public Subject[] findSubjects(@RequestParam(required = false) String subjectCode, @PathVariable String clientType) {
		return service.findSubjects(subjectCode, clientType);
	}

}
