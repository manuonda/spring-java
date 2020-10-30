package com.manuonda.dependency.injection.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.style.ValueStyler;
import org.springframework.stereotype.Service;

@Service	
@Profile({"dev","default"})
public class QaEnvironment implements EnvironmentService{
  
private static final Logger log = LoggerFactory.getLogger(QaEnvironment.class);

	@Override
	public String getEnvironment() {
		log.info("QaEnvironment");
		return "QA";
	}

}