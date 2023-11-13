package com.cs3343.demo;

import com.cs3343.demo.core.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.cs3343.demo.core"
})
public class CourseprojectApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(CourseprojectApplication.class, args);
//	}
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	public static void main(String[] args) {
		logger.info("STARTING THE APPLICATION");
		SpringApplication.run(CourseprojectApplication.class, args);
		logger.info("APPLICATION FINISHED");
	}


}
