package com.tplnt.studentscore.service;

import com.tplnt.studentscore.entity.Subject;

public interface SubjectServiceMs {
	
	Subject[] findSubjects(String subjectCode , String clientType);
	
	Subject findSubject(Long id , String clientType);

}
