package com.javacodegeeks.camel;

import java.util.Date;

public class CourseSchedule {
	private String trainer;
	private String course;
	private Date trainingDate;
	public CourseSchedule(String trainer, String course) {
		super();
		this.trainer = trainer;
		this.course = course;
	}
	public Date getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}
	public String getTrainer() {
		return trainer;
	}
	public String getCourse() {
		return course;
	}
	public String toString() {
		return "Course " + course + " will start on " + trainingDate + ", trainer is " + trainer;
	}
}
