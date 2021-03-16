package br.gov.go.goiania.atendefacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class EatendefacilApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EatendefacilApplication.class);
	} 

	public static void main(String[] args) {
		SpringApplication.run(EatendefacilApplication.class, args);
	}
	
}
