package com.tplnt.studentscore.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tplnt.studentscore.entity.Score;
import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.entity.dto.ScoreDto;
import com.tplnt.studentscore.entity.dto.ScoreManyDto;
import com.tplnt.studentscore.entity.dto.ScoreManyUpdateDto;
import com.tplnt.studentscore.entity.dto.StudentScore;
import com.tplnt.studentscore.entity.dto.StudentScoreBasic;
import com.tplnt.studentscore.entity.dto.StudentSubjectScoreToUpdate;
import com.tplnt.studentscore.entity.dto.SubjectScoreDto;
import com.tplnt.studentscore.entity.dto.SubjectScoreToUpdateDto;
import com.tplnt.studentscore.entity.dto.SubjectScoreUpdateDto;
import com.tplnt.studentscore.entity.exception.ScoreNotFoundException;
import com.tplnt.studentscore.repository.ScoreJpaRepository;
import com.tplnt.studentscore.repository.ScoreRepository;
import com.tplnt.studentscore.service.ScoreService;
import com.tplnt.studentscore.service.StudentService;
import com.tplnt.studentscore.service.SubjectService;
import com.tplnt.studentscore.util.StudentScoreUtil;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private StudentScoreUtil studentScoreUtil;
	
	private ScoreRepository repository;
	private ScoreJpaRepository jpaRepository;
	private StudentService studentService;
	private SubjectService subjectService;
	
	public ScoreServiceImpl(StudentService studentService, SubjectService subjectService, ScoreRepository repository, ScoreJpaRepository jpaRepository) {
		this.repository = repository;
		this.jpaRepository = jpaRepository;		
		this.studentService = studentService;
		this.subjectService = subjectService;
	}
	
	@Override
	public List<Score> findScores(Long studentId) {
		Student student = new Student();
		student.setId(studentId);
		if (studentId != null) {
			return jpaRepository.findByStudent(student);
		}
		else {
			return jpaRepository.findAll();
		}
	}
	
	@Override
	public Score findScore(Long id) {
		Optional<Score> score = jpaRepository.findById(id);
		
		if (score.isPresent()) {
			return score.get();
		}
		else {
			throw new ScoreNotFoundException(id);
		}
	}
	
	@Transactional
	@Override
	public Score saveScore(ScoreDto scoreDto) {
		Score score = new Score();
		
		Student student = new Student();
		student.setId(scoreDto.getStudentId());
		score.setStudent(student);
		
		Subject subject = new Subject();
		subject.setId(scoreDto.getSubjectId());
		score.setSubject(subject);
		
		score.setMark(scoreDto.getOverallMark());
		
		score = jpaRepository.save(score);
		return score;
	}
	
	@Transactional
	@Override
	public ScoreManyDto saveManyScore(ScoreManyDto scoreDto) {
		List<Score> scores = new ArrayList<>();
		
		for (SubjectScoreDto subjectScoreDto: scoreDto.getSubjectScores()) {
			Score score = new Score();

			Student student = new Student();
			student.setId(scoreDto.getStudentId());
			score.setStudent(student);
			
			Subject subject = new Subject();
			subject.setId(subjectScoreDto.getSubjectId());
			score.setSubject(subject);
			
			score.setMark(subjectScoreDto.getOverallMark());
			scores.add(score);
		}
		jpaRepository.saveAll(scores);
		
		return scoreDto;
	}
	
	@Override
	public StudentSubjectScoreToUpdate canUpdateScore(Long studentId) {
		Student student = studentService.findOneStudent(studentId);
		List<Subject> subjects = subjectService.findAllSubjectNoLimit();
		System.out.println(subjects);
		List<Score> studentScore = jpaRepository.findByStudent(student);
		List<SubjectScoreToUpdateDto> subjectScoreToUpdateDtos = new ArrayList<>();
		
		for (Subject subject : subjects) {
			
			SubjectScoreToUpdateDto scoreToUpdate = new SubjectScoreToUpdateDto();
			scoreToUpdate.setSubjectId(subject.getId());
			scoreToUpdate.setSubjectCode(subject.getCode());
			scoreToUpdate.setSubjectTitle(subject.getTitle());
			
			for (Score score : studentScore) {
				if (score.getSubject().getId() == subject.getId()) {
					scoreToUpdate.setScoreId(score.getId());
					scoreToUpdate.setOverallMark(score.getMark());
				}
			}
			
			subjectScoreToUpdateDtos.add(scoreToUpdate);
		}
		
		StudentSubjectScoreToUpdate studentSubjectScoreToUpdate = new StudentSubjectScoreToUpdate();
		studentSubjectScoreToUpdate.setStudentId(studentId);
		studentSubjectScoreToUpdate.setSubjectScoreToUpdateDtos(subjectScoreToUpdateDtos);
				
		return studentSubjectScoreToUpdate;
	}
	
	@Transactional
	@Override
	public ScoreManyUpdateDto updateManyScore(Long studentId, ScoreManyUpdateDto scoreDto) {
		
		Student existingStudent = studentService.findOneStudent(studentId);
		
		if (existingStudent != null) { }
		
		List<Score> scores = new ArrayList<>();
		
		for (SubjectScoreUpdateDto subjectScoreUpdateDto: scoreDto.getSubjectScores()) {
			Score score = new Score();

			Student student = new Student();
			student.setId(scoreDto.getStudentId());
			score.setStudent(student);
			
			Subject subject = new Subject();
			subject.setId(subjectScoreUpdateDto.getSubjectId());
			score.setSubject(subject);
			
			score.setId(subjectScoreUpdateDto.getScoreId());
			score.setMark(subjectScoreUpdateDto.getOverallMark());
			scores.add(score);
		}
		jpaRepository.saveAll(scores);
		
		return scoreDto;
	}
	
	@Override
	public StudentScore findStudentScore(Long studentId) {
		Student student = new Student();
		student.setId(studentId);
		List<Score> scores = jpaRepository.findByStudent(student);
		
		if (scores.size() < 1 && scores.isEmpty()) {
			throw new ScoreNotFoundException("Unknown Student Id");
		}
		
		StudentScore studentScore = new StudentScore();
		studentScoreUtil.setStudent(scores, studentScore);
		studentScoreUtil.setSubjectsAndScores(scores, studentScore);
		studentScoreUtil.calculateMeanAndTotalScore(scores, studentScore);
		studentScoreUtil.calculateMedian(scores, studentScore);
		studentScoreUtil.calculateMode(scores, studentScore);
		return studentScore;
	}
	
	@Override
	public StudentScoreBasic findStudentScoreBasic(Long studentId) {
		Student student = new Student();
		student.setId(studentId);
		List<Score> scores = jpaRepository.findByStudent(student);
		
		if (scores.size() < 1 && scores.isEmpty()) {
			throw new ScoreNotFoundException("Unknown Student Id");
		}
			
		StudentScoreBasic studentScore = new StudentScoreBasic();
		studentScore.setStudent(scores.get(0).getStudent());

		studentScoreUtil.setSubjectsAndScores(scores, studentScore);
		studentScoreUtil.calculateMeanAndTotalScore(scores, studentScore);
		return studentScore;
	}
	
	@Override
	@Transactional
	public boolean deleteManyScore(Long[] scoreIds) {
		return jpaRepository.deleteManyScore(Arrays.asList(scoreIds));
	}
	
	@Override
	public boolean canDeleteAllScore() {
		return repository.canDeleteAllScore();
	}
	
	
	@Override
	@Transactional
	public boolean deleteAllScore() {
		return repository.deleteAllScore();
	}

}
