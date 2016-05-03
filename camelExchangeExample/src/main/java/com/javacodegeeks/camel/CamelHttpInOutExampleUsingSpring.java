package com.javacodegeeks.camel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.util.CollectionStringBuffer;
import org.apache.camel.util.IOHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelHttpInOutExampleUsingSpring {
	public static final void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"httpInOutApplicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(
				appContext, false);
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();
			InputStream is = (InputStream) template.sendBody("direct:start", ExchangePattern.InOut, "Camel examples");
			System.out.println("Body: " + toString(is, 1).substring(0, 100));
		} finally {
			camelContext.stop();
		}
	}
	
    private static String toString(InputStream input, int noOfLines) throws IOException {
        BufferedReader reader = IOHelper.buffered(new InputStreamReader(input));
        CollectionStringBuffer builder = new CollectionStringBuffer();
        int i = 1;
        while (true) {        	
            String line = reader.readLine();
            if (line == null) {
                return builder.toString();
            }
            builder.append(line);
            if (i == noOfLines) {
            	break;
            }
            i++;
        }
        return builder.toString();
    }
}
