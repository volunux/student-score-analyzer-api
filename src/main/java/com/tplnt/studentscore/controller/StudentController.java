package com.tplnt.studentscore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tplnt.studentscore.entity.Student;
import com.tplnt.studentscore.entity.dto.EntityRemover;
import com.tplnt.studentscore.entity.dto.StudentDto;
import com.tplnt.studentscore.entity.dto.StudentUpdateDto;
import com.tplnt.studentscore.service.StudentService;

@RestController
@RequestMapping(value = "student" ,
				consumes = { MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_JSON_VALUE } )
public class StudentController {
	
	private StudentService service;
		
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@GetMapping({"", "/entries"})
	public List<Student> findStudents(@RequestParam(required = false, name ="search") String email,
									  @RequestParam(required = false, defaultValue = "0" , name = "page") Integer pageNumber) {
		return service.findAllStudent(email, pageNumber);
	}
	
	@GetMapping("/detail/{id}")
	public Student findStudent(@PathVariable(name = "id", required = true) Long id) {
		return service.findOneStudent(id);
	}
	
	@PostMapping({"", "/save"})
	public Student saveStudent(@Valid @RequestBody StudentDto studentDto) {
		return service.saveStudent(studentDto);
	}
	
	@PutMapping({"/id" , "/update/{id}"})
	public Student updateStudent(@PathVariable(name ="id", required = true) Long id, @Valid @RequestBody StudentUpdateDto studentUpdateDto) {
		return service.updateStudent(id, studentUpdateDto);
	}
	
	@DeleteMapping({"/{id}" , "/delete/{id}"})
	public boolean deleteStudent(@PathVariable(name ="id", required = true) Long id) {
		return service.deleteStudent(id);
	}
	
	@PutMapping("/remove/many")
	public boolean deleteManyStudent(@Valid @RequestBody EntityRemover entityRemover) {
		return service.deleteManyStudent(entityRemover.getIds());
	}
	
	@GetMapping("/delete/entries/all")
	public boolean canDeleteAllStudent() {
		return service.canDeleteAllStudent();
	}
	
	@DeleteMapping("/delete/entries/all")
	public boolean deleteAllStudent() {
		return service.deleteAllStudent();
	}
	
	@GetMapping("/exist/email/{email}")
	public boolean existStudentEmail(@PathVariable("email") String emailAddress) {
		return service.existStudentEmail(emailAddress);
	}

}
