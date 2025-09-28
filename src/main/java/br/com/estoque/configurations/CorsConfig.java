package br.com.estoque.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    WebMvcConfigurer corsConfigurer() {
		  
	        return new WebMvcConfigurer() {
	          
	        	@Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedOrigins("*") // URL do Angular
	                        .allowedMethods("*")
	                        .allowedHeaders("*");
	            }
	        };
	    }
	}