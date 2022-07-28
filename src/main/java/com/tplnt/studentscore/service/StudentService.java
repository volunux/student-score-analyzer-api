package com.tplnt.studentscore.service;

import java.util.List;

import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.dto.StudentDto;
import com.tplnt.studentscore.entity.dto.StudentUpdateDto;

public interface StudentService {
	
	List<Student> findAllStudent(String email, Integer pageNumber);
	
	Student findOneStudent(Long id);
	
	Student saveStudent(StudentDto studentDto);
	
	Student updateStudent(Long id, StudentUpdateDto studentUpdateDto);
	
	boolean deleteStudent(Long id);
	
	boolean deleteManyStudent(Long[] studentIds);
	
	boolean canDeleteAllStudent();

	boolean deleteAllStudent();

	boolean existStudentEmail(String emailAddress);
}
