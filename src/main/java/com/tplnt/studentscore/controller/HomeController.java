package com.tplnt.studentscore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tplnt.studentscore.entity.StudentAnalyzer;

@RestController("")
public class HomeController {

	@GetMapping
	public StudentAnalyzer home() {
		return new StudentAnalyzer();
	}

}
