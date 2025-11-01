package br.com.estoque.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
    	var openApi = new OpenAPI() 
    			.components(new Components())
    			.info(new Info()
    			.title("API Controle de Estoque - Torres")
    			.description("API Spring Boot para controle de estoque.")
    			.contact(new Contact()
    	                .name("")
    	                .url("")
    	                .email("joadsontorres6@gmail.com"))
    			 .version("1.0.0"));
    	
    	return openApi;
    }
}
