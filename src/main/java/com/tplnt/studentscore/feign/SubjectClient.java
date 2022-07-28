package com.tplnt.studentscore.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tplnt.studentscore.entity.Subject;

@FeignClient(name = "subject-service", path = "/api/v1/subject")
public interface SubjectClient {

	@RequestMapping(method = RequestMethod.GET,
					value = "/entries",
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Subject[] findSubjects(@RequestParam(required = false) String subjectCode);
	
	@RequestMapping(method = RequestMethod.GET,
					value = "/detail/{id}")
	public Subject findSubject(@RequestParam(required = true) Long id);
}
