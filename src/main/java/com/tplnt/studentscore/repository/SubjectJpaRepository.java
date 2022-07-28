package com.tplnt.studentscore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tplnt.studentscore.entity.Subject;

public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {

  List<Subject> findByCode(String code, Pageable pageable);

}
