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

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.entity.dto.EntityRemover;
import com.tplnt.studentscore.entity.dto.SubjectDto;
import com.tplnt.studentscore.entity.dto.SubjectUpdateDto;
import com.tplnt.studentscore.service.SubjectService;

@RestController
@RequestMapping(value = "subject",
				consumes = { MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_JSON_VALUE } )
public class SubjectController {
	
	private SubjectService service;

	public SubjectController(SubjectService service) {
		this.service = service;
	}
	
    @GetMapping({ "", "/entries" })
    public List<Subject> findSubjects(@RequestParam(required = false, name = "search") String subjectCode,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer pageNumber) {
        return service.findAllSubject(subjectCode, pageNumber);
    }
    
    @GetMapping({"/{id}" , "/detail/{id}"})
    public Subject findSubject(@PathVariable("id") Long id) {
        return service.findSubject(id);
    }

    @PostMapping({"" , "/save"})
    public Subject saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return service.saveSubject(subjectDto);
    }

    @PutMapping({"/{id}" , "/update/{id}"})
    public Subject updateSubject(@PathVariable("id") Long id, @Valid @RequestBody SubjectUpdateDto subjectUpdateDto) {
        return service.updateSubject(id, subjectUpdateDto);
    }

    @DeleteMapping({"/{id}" , "/delete/{id}"})
    public boolean deleteSubject(@PathVariable("id") Long id) {
        return service.deleteSubject(id);
    }
    
	@PutMapping("/remove/many")
	public boolean deleteManySubject(@Valid @RequestBody EntityRemover entityRemover) {
		return service.deleteManySubject(entityRemover.getIds());
	}
	
	@GetMapping("/delete/entries/all")
	public boolean canDeleteAllSubject() {
		return service.canDeleteAllSubject();
	}
	
	@DeleteMapping("/delete/entries/all")
	public boolean deleteAllSubject() {
		return service.deleteAllSubject();
	}

    @GetMapping("/exist/code/{code}")
    public boolean existSubjectCode(@PathVariable("code") String subjectCode) {
        return service.existSubjectCode(subjectCode);
    }

}
