package com.cs3343.demo;

import com.cs3343.demo.core.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
		"com.cs3343.demo.core",
},exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
public class CourseprojectApplication {
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	public static void main(String[] args) {
		logger.info("STARTING THE APPLICATION");
        try {
            SpringApplication.run(CourseprojectApplication.class, args);

        } catch (Exception ex) {
			System.out.println(ex);
        }
		logger.info("APPLICATION FINISHED");
	}


}
