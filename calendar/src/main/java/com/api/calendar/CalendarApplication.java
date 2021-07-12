package com.api.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.api.calendar.repository"})
@EntityScan("com.api.calendar")
//@EnableJpaRepositories("com.delivery.repository")
public class CalendarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CalendarApplication.class, args);
	}

}
