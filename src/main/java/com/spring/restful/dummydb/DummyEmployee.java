package com.spring.restful.dummydb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.spring.restful.model.Employee;
import com.spring.restful.repository.EmployeeRepository;

@Configuration
@Profile("dev")
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar", 19)));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief", 20)));
		};
	}
}