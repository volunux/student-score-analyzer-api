package com.tplnt.studentscore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tplnt.studentscore.entity.Score;
import com.tplnt.studentscore.entity.dto.BaseStudentScore;
import com.tplnt.studentscore.entity.dto.StudentScore;
import com.tplnt.studentscore.entity.dto.SubjectScore;

@Component
public class StudentScoreUtil {
	
	public void setStudent(List<Score> scores, StudentScore studentScore) {		
		
		if (scores.size() > 0 && !scores.isEmpty()) {
			studentScore.setStudent(scores.get(0).getStudent());
		}
	}
	
	public void setSubjectsAndScores(List<Score> scores, BaseStudentScore studentScore) {		
		ArrayList<SubjectScore> subjectsScore = new ArrayList<>();
		for (Score score : scores) {
			SubjectScore subjectScore = new SubjectScore();
			subjectScore.setSubjectTitle(score.getSubject().getTitle());
			subjectScore.setSubjectCode(score.getSubject().getCode());
			subjectScore.setSubjectYear(score.getSubject().getYear());
			subjectScore.setScore(score.getMark());
			subjectsScore.add(subjectScore);
		}

		studentScore.setSubjectsScore(subjectsScore);
	}

	public void calculateMeanAndTotalScore(List<Score> scores, BaseStudentScore studentScore) {
		
		Integer totalScore = 0;
		for (Score score : scores) {
			totalScore += score.getMark();
		}
		
		studentScore.setTotalScore(totalScore);
		studentScore.setMeanScore((double) totalScore / (double) scores.size());
	}
	
	public void calculateMedian(List<Score> scores, StudentScore studentScore) {
		
		List<Integer> marks = new ArrayList<>();
		Integer[] markArr = new Integer[scores.size()];
		
		for (Score score : scores) {
			marks.add(score.getMark());
		}
		
		markArr = marks.toArray(markArr);
		Arrays.sort(markArr);

		double median = 0.0;
		if (markArr.length % 2 != 0) {
			median = (double) markArr[scores.size() / 2];
		}
		else {
			median = (double) (markArr[(scores.size() - 1) / 2] + markArr[scores.size() / 2]) / 2.0;
		}

		studentScore.setMedianScore(median);		
	}
	
	public void calculateMode(List<Score> scores, StudentScore studentScore) {
		List<Integer> marks = new ArrayList<>();
		Integer[] markArr = new Integer[scores.size()];
		
		for (Score score : scores) {
			marks.add(score.getMark());
		}
		
		markArr = marks.toArray(markArr);

	    Map<Integer, Integer> values = new HashMap<Integer, Integer>();
	 
	    for (int i=0; i < markArr.length; ++i) {
	        if (values.get(markArr[i]) == null) {
	            values.put(markArr[i], 1);
	        } else {
	            values.put(markArr[i], values.get(markArr[i]) + 1);
	        }
	    }

	    int greatestTotal = 0;
	    int mode = 0;

	    Iterator<Map.Entry<Integer, Integer>> it = values.entrySet().iterator();

	    while (it.hasNext()) {

	    	Map.Entry<Integer, Integer> pair = it.next();
	        
	    	if (pair.getValue() > greatestTotal) {

	    		mode =  pair.getKey();
	            greatestTotal = pair.getValue();
	        }
	    	
	        it.remove();
	    }
	    studentScore.setModeScore(mode);
	}
}
