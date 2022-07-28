package com.tplnt.studentscore.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tplnt.studentscore.repository.ScoreRepository;

@Repository
public class ScoreRepositoryImpl implements ScoreRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean canDeleteAllScore() {
		Query query = entityManager.createQuery("select s.id FROM Score s WHERE id is not null");
		query.setMaxResults(1);
		if (query.getResultList().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean deleteAllScore() {
		Query query = entityManager.createQuery("delete from Score");
		if (query.executeUpdate() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
