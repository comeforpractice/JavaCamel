package com.javacodegeeks.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelChangeRouteInOutExampleUsingSpring {
	public static final void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"changeRouteToInOutApplicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();
			template.sendBody("activemq:queue:inboundQueue", "InOut example");
			Thread.sleep(1000);
		} finally {
			camelContext.stop();
		}
	}	
}
