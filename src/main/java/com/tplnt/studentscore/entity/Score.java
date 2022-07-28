package com.tplnt.studentscore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name ="score", 
		uniqueConstraints = { @UniqueConstraint(columnNames = {"subject_id", "student_id"} , name ="uqk_subject_student") })
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(
				name = "student_id", nullable = false, insertable = true, updatable = false)
	@OnDelete(
				action = OnDeleteAction.CASCADE)
	private Student student;
	
	@ManyToOne
	@JoinColumn(
				name ="subject_id", nullable = false, updatable = false
			)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Subject subject;
	
	@Column(name ="total_mark")
	private Integer mark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", updatable = false)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Date updatedOn;

	public Score() {
		this.createdOn = new Date();
		this.updatedOn = new Date();
	}
	
}
