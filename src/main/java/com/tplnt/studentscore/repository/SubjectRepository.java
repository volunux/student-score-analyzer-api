package com.tplnt.studentscore.repository;

import java.util.List;

import com.tplnt.studentscore.entity.Subject;

public interface SubjectRepository {

    List<Subject> getAllSubjects(String subjectCode);

    Subject getOneSubject(Long id);

    Subject createSubject(Subject subject);

    Subject updateSubject(Long id, Subject subject);

    boolean deleteSubject(Long id);
    
	boolean deleteManySubject(List<Long> subjectIds);
    
	boolean canDeleteAllSubject();

	boolean deleteAllSubject();

    boolean existSubjectCode(String subjectCode);
}
