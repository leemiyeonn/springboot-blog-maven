package com.blogMaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication       // (exclude = SecurityAutoConfiguration.class)
public class BlogMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogMavenApplication.class, args);
	}

}
