package com.tplnt.studentscore.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tplnt.studentscore.entity.Student;

@Repository
public interface StudentRepository {
	
	List<Student> getAllStudent(String email);
		
	Student getOneStudent(Long id);
	
	Student createStudent(Student student);
	
	Student updateStudent(Long id, Student student);
	
	boolean deleteStudent(Long id);
	
	boolean deleteManyStudent(List<Long> studentIds);
	
	boolean canDeleteAllStudent();

	boolean deleteAllStudent();
	
	boolean existStudentEmail(String emailAddress);
}
