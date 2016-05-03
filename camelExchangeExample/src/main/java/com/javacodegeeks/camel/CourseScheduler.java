package com.javacodegeeks.camel;

import java.util.Date;

public class CourseScheduler {
	public CourseSchedule schedule(CourseSchedule courseSchedule) {
		String course = courseSchedule.getCourse();
		String trainer = courseSchedule.getTrainer();
		System.out.println("Schedule " + course + ", trainer is " + trainer);
		courseSchedule.setTrainingDate(new Date());
		return courseSchedule; 
	}
	
	public String reSchedule(String course) {
		System.out.println("Re-schedule " + course);
		return course;
	}
}
