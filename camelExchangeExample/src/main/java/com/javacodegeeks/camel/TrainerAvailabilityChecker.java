package com.javacodegeeks.camel;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TrainerAvailabilityChecker implements Processor {
	public void process(Exchange exchange) throws Exception {		
		String course = exchange.getIn().getBody(String.class);
		System.out.println("Check availability of trainer for " + course);
		String trainer = TRAINERS.get(course);
		if (trainer == null) {
			throw new TrainerNotAvailableException(exchange, "No trainer for " + course);
		}
		System.out.print("Found Trainer: " );
		exchange.getOut().setBody(new CourseSchedule(trainer, course), CourseSchedule.class);
	}
	
	private static final Map<String, String> TRAINERS = new HashMap<String, String>(); 
	
	static {
		TRAINERS.put("Scala", "Joe");
		TRAINERS.put("Java Core", "Sam");
		TRAINERS.put("Mockito", "Krish");
	}
}
