package com.manuonda.dependency.injection.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEnvironment implements EnvironmentService {

	private static final Logger log = LoggerFactory.getLogger(ProdEnvironment.class);

	@Override
	public String getEnvironment() {
		log.info("ProdEnvironment");
		return "Prod";
	}

}
