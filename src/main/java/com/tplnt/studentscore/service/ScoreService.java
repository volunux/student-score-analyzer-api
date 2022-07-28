package com.tplnt.studentscore.service;

import java.util.List;

import com.tplnt.studentscore.entity.Score;
import com.tplnt.studentscore.entity.dto.ScoreDto;
import com.tplnt.studentscore.entity.dto.ScoreManyDto;
import com.tplnt.studentscore.entity.dto.ScoreManyUpdateDto;
import com.tplnt.studentscore.entity.dto.StudentScore;
import com.tplnt.studentscore.entity.dto.StudentScoreBasic;
import com.tplnt.studentscore.entity.dto.StudentSubjectScoreToUpdate;

public interface ScoreService {
	
	List<Score> findScores(Long studentId);
	
	Score findScore(Long id);
	
	Score saveScore(ScoreDto scoreDto);
	
	StudentScore findStudentScore(Long studentId);
	
	StudentScoreBasic findStudentScoreBasic(Long studentId);

	ScoreManyDto saveManyScore(ScoreManyDto scoreManyDto);
	
	StudentSubjectScoreToUpdate canUpdateScore(Long studentId);
	
	ScoreManyUpdateDto updateManyScore(Long studentId, ScoreManyUpdateDto scoreManyUpdateDto);
	
	boolean deleteManyScore(Long[] scoreIds);
	
	boolean canDeleteAllScore();

	boolean deleteAllScore();

}
