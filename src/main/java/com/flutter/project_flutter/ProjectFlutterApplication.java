package com.flutter.project_flutter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectFlutterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectFlutterApplication.class, args);
	}

}
