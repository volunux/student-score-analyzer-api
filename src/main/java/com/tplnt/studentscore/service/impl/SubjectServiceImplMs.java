package com.tplnt.studentscore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tplnt.studentscore.controller.util.SubjectClientUtil;
import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.service.SubjectServiceMs;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Qualifier("subject-ms")
public class SubjectServiceImplMs implements SubjectServiceMs {
	
	@Autowired
	private SubjectClientUtil subjectClientUtil;
	
	@CircuitBreaker(name ="subject-service" , fallbackMethod = "findSubjectsFallback")
	@Override
	public Subject[] findSubjects(String subjectCode, String clientType) {
		switch (clientType) {
		case "feign":
			return subjectClientUtil.findSubjectsByFeign(subjectCode);

		default:
			return subjectClientUtil.findSubjectsByRest(subjectCode);
		}
	}
	
	public Subject[] findSubjectsFallback(String subjectCode, String clientType, Throwable t) {
		return new Subject[] {};
	}
	
	@CircuitBreaker(name ="subject-service", fallbackMethod = "findSubjectFallback")
	@Override
	public Subject findSubject(Long id, String clientType) {
		switch (clientType) {
		case "feign":
			return subjectClientUtil.findSubjectByFeign(id);

		default:
			return subjectClientUtil.findSubjectByRest(id);
		}
	}
	
	public Subject findSubjectFallback(Long id, String clientType, Throwable t) {
		return null;
	}

}
