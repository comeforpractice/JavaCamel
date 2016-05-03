package com.javacodegeeks.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

public class CamelProcessorExample {
	public static void main(String[] args) throws Exception {
		JndiContext jndiContext = new JndiContext();
		jndiContext.bind("scheduleCourse", new CourseScheduler());
		CamelContext camelContext = new DefaultCamelContext(jndiContext);
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {										
					from("direct:course_name")
					   .onException(TrainerNotAvailableException.class)
					   .handled(true)
					   .transform(constant("No trainer available exception"))
					   .to("stream:out")
					   .end()
					.process(new TrainerAvailabilityChecker())					
					.to("bean:scheduleCourse?method=schedule")
					.to("stream:out");
				}
			});
			camelContext.start();	
			ProducerTemplate template = camelContext.createProducerTemplate();
			template.sendBody("direct:course_name", "Scala");
			try {
				template.sendBody("direct:course_name", "Spring Integration");
			} catch (Throwable e) {
				System.out.println("Exception " + e.getMessage());
			}
		} finally {
			camelContext.stop();
		}
	}
}
