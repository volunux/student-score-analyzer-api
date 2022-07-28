package com.tplnt.studentscore.repository.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.exception.StudentDuplicateEntityException;
import com.tplnt.studentscore.repository.StudentRepository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Student getOneStudent(Long id) {
		return entityManager.find(Student.class, id);
	}
	
	@Override
	public List<Student> getAllStudent(String email) {
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
		if (email != null && !email.isEmpty()) {
			query = entityManager.createQuery("select s from Student s where s.emailAddress = :email", Student.class);
			query.setParameter("email", email);
		}
		return query.getResultList();
	}
	
	@Override
	public Student createStudent(Student student) {
		try {
			entityManager.persist(student);
			return student;
		}
		catch (EntityExistsException ex) {
			throw new StudentDuplicateEntityException();
		}
	}
	
	@Override
	public Student updateStudent(Long id, Student student) {
		return entityManager.merge(student);
	}
	
	@Override
	public boolean deleteStudent(Long id) {
		try {
			Student student = entityManager.getReference(Student.class, id);
			entityManager.remove(student);
			return true;
		}
		catch (EntityNotFoundException ex) {
			return false;
		}
	}
	
	@Override
	public boolean deleteManyStudent(List<Long> studentIds) {
		Query query = entityManager.createQuery("delete from Student s where s.id in (:ids)");
		query.setParameter("ids", studentIds);
		if (query.executeUpdate() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean canDeleteAllStudent() {
		Query query = entityManager.createQuery("select s.id FROM Student s WHERE id is not null");    
		query.setMaxResults(1);
		if (query.getResultList().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean deleteAllStudent() {
		Query query = entityManager.createQuery("delete from Student");
		if (query.executeUpdate() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean existStudentEmail(String emailAddress) {
		Query query = entityManager.createQuery("select s.id from Student s where s.emailAddress = :email");
		query.setMaxResults(1);
		query.setParameter("email", emailAddress);
		if (query.getResultList().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}
