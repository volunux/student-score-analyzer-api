package com.tplnt.studentscore.service;

import java.util.List;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.entity.dto.SubjectDto;
import com.tplnt.studentscore.entity.dto.SubjectUpdateDto;

public interface SubjectService {
	
	List<Subject> findAllSubject(String subjectCode, Integer pageNumber);

	List<Subject> findAllSubjectNoLimit();
	
	Subject findSubject(Long id);
	
	Subject saveSubject(SubjectDto subjectDto);
	
	Subject updateSubject(Long id, SubjectUpdateDto subjectUpdateDto);
	
	boolean deleteSubject(Long id);
	
	boolean deleteManySubject(Long[] subjectIds);
	
	boolean canDeleteAllSubject();

	boolean deleteAllSubject();

	boolean existSubjectCode(String subjectCode);

}
