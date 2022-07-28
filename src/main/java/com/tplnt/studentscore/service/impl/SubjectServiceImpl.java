package com.tplnt.studentscore.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.entity.dto.SubjectDto;
import com.tplnt.studentscore.entity.dto.SubjectUpdateDto;
import com.tplnt.studentscore.entity.exception.SubjectNotFoundException;
import com.tplnt.studentscore.repository.SubjectJpaRepository;
import com.tplnt.studentscore.repository.SubjectRepository;
import com.tplnt.studentscore.service.SubjectService;

@Service
@Primary
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SubjectJpaRepository jpaRepository;

	private SubjectRepository repository;
	
	public SubjectServiceImpl(SubjectRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Subject> findAllSubject(String code, Integer pageOffset) {
		PageRequest pageRequest = PageRequest.of(pageOffset, 10, Sort.by("updatedOn").descending());

		if (code != null) {
			return jpaRepository.findByCode(code, pageRequest);
		}
		return jpaRepository.findAll(pageRequest).getContent();
	}
	
	@Override
	public List<Subject> findAllSubjectNoLimit() {
		return repository.getAllSubjects(null);
	}

    @Override
    public Subject findSubject(Long id) {
        Subject subject = repository.getOneSubject(id);
        if (subject != null) {
        	return subject;
        }
        else {
        	throw new SubjectNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public Subject saveSubject(SubjectDto subjectDto) {
    	Subject subject = new Subject();
    	subject = modelMapper.map(subjectDto, Subject.class);
        return repository.createSubject(subject);
    }

    @Override
    @Transactional
    public Subject updateSubject(Long id, SubjectUpdateDto subjectUpdateDto) {
    	Subject existingSubject = this.findSubject(id);
    	if (existingSubject == null) {
    		throw new SubjectNotFoundException(id);
    	}
    	
    	Subject subject = new Subject();
    	subject = modelMapper.map(subjectUpdateDto, Subject.class);
    	subject.setId(id);
        return repository.updateSubject(id, subject);
    }

    @Override
    @Transactional
    public boolean deleteSubject(Long id) {
        boolean removed = repository.deleteSubject(id);
        
        if (removed) {
        	return removed;
        }
        else {
        	throw new SubjectNotFoundException(id);
        }
    }
    
	@Override
	@Transactional
	public boolean deleteManySubject(Long[] subjectIds) {
		return repository.deleteManySubject(Arrays.asList(subjectIds));
	}
	
	@Override
	public boolean canDeleteAllSubject() {
		return repository.canDeleteAllSubject();
	}
	
	
	@Override
	@Transactional
	public boolean deleteAllSubject() {
		return repository.deleteAllSubject();
	}

	@Override
	public boolean existSubjectCode(String subjectCode) {
		return repository.existSubjectCode(subjectCode);
	}
	
}
