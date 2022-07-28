package com.tplnt.studentscore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tplnt.studentscore.entity.Score;
import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.dto.EntityRemover;
import com.tplnt.studentscore.entity.dto.ScoreDto;
import com.tplnt.studentscore.entity.dto.ScoreManyDto;
import com.tplnt.studentscore.entity.dto.ScoreManyUpdateDto;
import com.tplnt.studentscore.entity.dto.StudentScore;
import com.tplnt.studentscore.entity.dto.StudentScoreBasic;
import com.tplnt.studentscore.entity.dto.StudentSubjectScoreToUpdate;
import com.tplnt.studentscore.service.ScoreService;
import com.tplnt.studentscore.service.StudentService;

@RestController
@RequestMapping(value ="score" ,
				consumes = { MediaType.APPLICATION_JSON_VALUE } ,
				produces = { MediaType.APPLICATION_JSON_VALUE })
public class ScoreController {
	
	private ScoreService service;
	private StudentService studentService;
	
	public ScoreController(ScoreService service, StudentService studentService) {
		this.service = service;
		this.studentService = studentService;
	}

	@GetMapping("/student/entries")
	public List<Student> findStudents(@RequestParam(required = false, name = "search") String email,
			@RequestParam(required = false, defaultValue = "0", name = "page") Integer pageNumber) {
		return studentService.findAllStudent(email, pageNumber);
	}
	
	@GetMapping("/student/update/{id}")
	public StudentSubjectScoreToUpdate canUpdateScore(@PathVariable("id") Long studentId) {
		return service.canUpdateScore(studentId);
	}
	
	@PutMapping("/update/{id}")
	public ScoreManyUpdateDto updateManyScore(@PathVariable("id") Long studentId, @Valid @RequestBody ScoreManyUpdateDto scoreDto) {
		return service.updateManyScore(studentId, scoreDto);
	}
	
	@GetMapping("/student/detail/{id}")
	public StudentScoreBasic findStudentScoresDetail(@PathVariable("id") Long studentId) {
		return service.findStudentScoreBasic(studentId);
	}
	
	@GetMapping("/student/analysis/{id}")
	public StudentScore findStudentScoreAnalysis(@PathVariable("id") Long studentId) {
		return service.findStudentScore(studentId);
	}
	
	@GetMapping("/delete/entries/all")
	public boolean canDeleteAllScore() {
		return service.canDeleteAllScore();
	}
	
	@DeleteMapping("/delete/entries/all")
	public boolean deleteAllScore() {
		return service.deleteAllScore();
	}

	public ScoreManyDto saveManyScore(@Valid @RequestBody ScoreManyDto scoreDto) {
		return service.saveManyScore(scoreDto);
	}

	public boolean deleteManyScore(@Valid @RequestBody EntityRemover entityRemover) {
		return service.deleteManyScore(entityRemover.getIds());
	}

	public Score saveScore(@Valid @RequestBody ScoreDto scoreDto) {
		return service.saveScore(scoreDto);
	}

	public Score findScore(@PathVariable Long id) {
		return service.findScore(id);
	}

	public List<Score> findScores(@RequestParam(value = "id", required = false) Long studentId) {
		return service.findScores(studentId);
	}
}
