package com.pomoravskivrbaci.cinemareservations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Seat implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Column(name="regNumber")
	protected String regNumber;
	

	@JsonIgnore
	@ManyToOne
	protected HallSegment hallSegment;


	protected boolean free;
	
	
	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Seat() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}


	public void setHallSegment(HallSegment hallSegment) {
		this.hallSegment = hallSegment;
	}
	@JsonIgnore
	public HallSegment getHallSegment() {
		return hallSegment;
	}
}
