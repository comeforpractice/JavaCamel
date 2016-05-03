package com.javacodegeeks.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HttpProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println("MIP of Http Endpoint is " + exchange.getPattern());
	}

}
