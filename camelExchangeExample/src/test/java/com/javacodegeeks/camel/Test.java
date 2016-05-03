package com.javacodegeeks.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test extends CamelTestSupport {

	@org.junit.Test
	public void testin() throws Exception{
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"inOnlyApplicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();
			template.sendBody("direct:start", "InOnly example");
			Thread.sleep(1000);
		}finally {
			camelContext.stop();
		}
	}
}
