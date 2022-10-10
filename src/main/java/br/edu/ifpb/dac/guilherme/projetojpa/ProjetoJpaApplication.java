package br.edu.ifpb.dac.guilherme.projetojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableWebMvc
public class ProjetoJpaApplication implements WebMvcConfigurer {
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}
	
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
		.allowedMethods("GET","POST","PUT","DELETE","OPTION","PATCH");
	}

}
