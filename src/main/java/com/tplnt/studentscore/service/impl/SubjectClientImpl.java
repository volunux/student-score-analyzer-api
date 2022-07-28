package com.tplnt.studentscore.service.impl;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.feign.SubjectClient;

public class SubjectClientImpl implements SubjectClient {
	
	@Override
	public Subject[] findSubjects(String subjectCode) {
		return null;
	}
	
	@Override
	public Subject findSubject(Long id) {
		return null;
	}

}
