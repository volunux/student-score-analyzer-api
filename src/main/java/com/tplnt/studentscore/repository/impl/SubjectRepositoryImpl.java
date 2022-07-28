package com.tplnt.studentscore.repository.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tplnt.studentscore.entity.Subject;
import com.tplnt.studentscore.entity.exception.SubjectDuplicateEntityException;
import com.tplnt.studentscore.repository.SubjectRepository;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

	@PersistenceContext
	private EntityManager entityManager;

    @Override
    public List<Subject> getAllSubjects(String subjectCode) {
    	System.out.println("Did I got here?");
    	TypedQuery<Subject> subjectQuery = entityManager.createQuery("select s from Subject s", Subject.class);
    	if (subjectCode != null) {
    		subjectQuery = entityManager.createQuery("select s from Subject s where s.code = :subjectCode", Subject.class);
    		subjectQuery.setParameter("subjectCode", subjectCode);
    	}
        return subjectQuery.getResultList();
    }

    @Override
    public Subject getOneSubject(Long id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public Subject createSubject(Subject subject) {
        try {
        	entityManager.persist(subject);
        	return subject;
        }
        catch (EntityExistsException ex) {
        	throw new SubjectDuplicateEntityException();
        }
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        return entityManager.merge(subject);
    }

    @Override
    public boolean deleteSubject(Long id) {
    	try {
            Subject subject = entityManager.getReference(Subject.class, id);
            entityManager.remove(subject);
            return true;
    	}
    	catch (EntityNotFoundException ex) {
    		return false;
    	}
    }
    
	@Override
	public boolean deleteManySubject(List<Long> subjectIds) {
		Query query = entityManager.createQuery("delete from Subject s where s.id in (:ids)");
		query.setParameter("ids", subjectIds);
		if (query.executeUpdate() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean canDeleteAllSubject() {
		Query query = entityManager.createQuery("select s.id FROM Subject s WHERE id is not null");    
		query.setMaxResults(1);
		if (query.getResultList().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean deleteAllSubject() {
		Query query = entityManager.createQuery("delete from Subject");
		if (query.executeUpdate() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean existSubjectCode(String subjectCode) {
		Query query = entityManager.createQuery("select s.id from Subject s where s.code = :code");
		query.setMaxResults(1);
		query.setParameter("code", subjectCode);
		if (query.getResultList().size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
