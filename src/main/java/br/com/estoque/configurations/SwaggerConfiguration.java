package br.com.estoque.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Controle de Estoque - Torres")
                .description("Documentação da API desenvolvida com Spring Boot")
                .version("1.0.0")
                .contact(new Contact()
                    .name("")
                    .url("")
                    .email("joadsontorres6@gmail.com")));
    }
}
