package com.terra.terra_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TerraSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerraSchedulerApplication.class, args);
	}

}
