package com.dev4j.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class FooService {

	@Autowired
	private Tracer tracer;
	
	private static final Logger log = LoggerFactory.getLogger(FooService.class);

	public void printLog() {
		
		log.info("Test Log");
		Span newSpan =  tracer.nextSpan().name("newSpan");
		try(Tracer.SpanInScope ws = tracer.withSpan(newSpan.start())) {
			log.info("Test log in new span start");
		} finally {
			newSpan.end();
		} 
		
		Span newSpan1 =  tracer.nextSpan().name("newSpan1");
		try(Tracer.SpanInScope ws = tracer.withSpan(newSpan1.start())) {
			log.info("Test log in new span1 start");
		} finally {
			newSpan1.end();
		} 
	}
}
