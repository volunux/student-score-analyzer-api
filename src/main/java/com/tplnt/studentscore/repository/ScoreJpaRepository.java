package com.tplnt.studentscore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tplnt.studentscore.entity.Score;
import com.tplnt.studentscore.entity.Student;

@Repository
public interface ScoreJpaRepository extends JpaRepository<Score, Long> {
		
	public List<Score> findByStudent(Student student);

	@Query("delete from Score s where s.id in (:ids)")
	boolean deleteManyScore(@Param("ids") List<Long> scoreIds);
	
	@Query("delete from Score")
	boolean deleteAllScore();

}
