package com.tplnt.studentscore.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.dto.StudentDto;
import com.tplnt.studentscore.entity.dto.StudentUpdateDto;
import com.tplnt.studentscore.entity.exception.StudentNotFoundException;
import com.tplnt.studentscore.repository.StudentJpaRepository;
import com.tplnt.studentscore.repository.StudentRepository;
import com.tplnt.studentscore.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentJpaRepository jpaRepository;
	
	private StudentRepository repository;
	
	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Student> findAllStudent(String email, Integer pageOffset) {
		PageRequest pageRequest = PageRequest.of(pageOffset, 10, Sort.by("updatedOn").descending());

		if (email != null) {
			return jpaRepository.findByEmailAddress(email, pageRequest);
		}
		return jpaRepository.findAll(pageRequest).getContent();
	}
	
	@Override
	public Student findOneStudent(Long id) {
		Student student = repository.getOneStudent(id);
		if (student != null) {
			return student;
		}
		else {
			throw new StudentNotFoundException(id);
		}
	}
	
	@Override
	@Transactional
	public Student saveStudent(StudentDto studentDto) {
		Student student = new Student();
		student = modelMapper.map(studentDto, Student.class);
		Student newStudent = repository.createStudent(student);
		return newStudent;
	}
	
	@Override
	@Transactional
	public Student updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
		Student existingStudent = repository.getOneStudent(id);
		if (existingStudent == null) {
			throw new StudentNotFoundException(id);
		}

		Student student = new Student();
		student = modelMapper.map(studentUpdateDto, Student.class);
		student.setId(id);
		return repository.updateStudent(id, student);
	}
	
	@Override
	@Transactional
	public boolean deleteStudent(Long id) {
		boolean removed = repository.deleteStudent(id);
		if (removed) {
			return removed;
		}
		else {
			throw new StudentNotFoundException(id);
		}
	}
	
	@Override
	@Transactional
	public boolean deleteManyStudent(Long[] studentIds) {
		return repository.deleteManyStudent(Arrays.asList(studentIds));
	}
	
	@Override
	public boolean canDeleteAllStudent() {
		return repository.canDeleteAllStudent();
	}
	
	@Override
	@Transactional
	public boolean deleteAllStudent() {
		return repository.deleteAllStudent();
	}
	
	@Override
	public boolean existStudentEmail(String emailAddress) {
		return repository.existStudentEmail(emailAddress);
	}

}
