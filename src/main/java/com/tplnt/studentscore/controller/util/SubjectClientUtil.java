package com.tplnt.studentscore.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.feign.SubjectClient;

@Component
public class SubjectClientUtil {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SubjectClient subjectClient;

	public Subject[] findSubjectsByFeign(String subjectCode) {
		return subjectClient.findSubjects(subjectCode);
	}
	
	public Subject[] findSubjectsByRest(String subjectCode) {
		String serviceUri = "http://subject-service/api/v1/subject/entries";
		ResponseEntity<Subject[]> restExchange = restTemplate.getForEntity(serviceUri, Subject[].class);
		return restExchange.getBody();
	}
	
	public Subject findSubjectByFeign(Long id) {
		return subjectClient.findSubject(id);
	}
	
	public Subject findSubjectByRest(Long id) {
		String serviceUri = "http://subject-service/api/v1/subject/detail/" + id;
		ResponseEntity<Subject> restExchange = restTemplate.getForEntity(serviceUri, Subject.class);
		return restExchange.getBody();
	}
}
