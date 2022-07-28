package com.tplnt.studentscore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tplnt.studentscore.entity.Student;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {

	List<Student> findByEmailAddress(String emailAddress, Pageable pageable);

}
