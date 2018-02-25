package com.waracle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.waracle.utils.InitialDataLoader;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.waracle.repository"})
public class Application {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Bean
	public CommandLineRunner demo(InitialDataLoader dataLoader) {
		return (args) -> {		
			String jsonCakeUrl ="https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
			dataLoader.loadsJsonCakeDataFromURL(jsonCakeUrl);
		};
	}

}
