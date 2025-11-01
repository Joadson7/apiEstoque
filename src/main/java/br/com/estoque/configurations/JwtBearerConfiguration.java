package br.com.estoque.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtBearerConfiguration {

	@Bean public FilterRegistrationBean<JwtBearerFilter> jwtFilter() { 
		
		//Registrando o filtro que irá validar os TOKENS 
		
		FilterRegistrationBean<JwtBearerFilter>
			filter = new FilterRegistrationBean<JwtBearerFilter>();
			filter.setFilter(new JwtBearerFilter()); 
			
		//Aplicando o filtro em toda a API 
			filter.addUrlPatterns("/api/*"); 
			
		return filter; } 
	}
	

