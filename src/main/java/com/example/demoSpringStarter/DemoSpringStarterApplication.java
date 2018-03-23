package com.example.demoSpringStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@ComponentScan({"walletController","DAO"})
@EntityScan("POJO")
@EnableJpaRepositories("DAO")
public class DemoSpringStarterApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringStarterApplication.class, args);
	}
}
