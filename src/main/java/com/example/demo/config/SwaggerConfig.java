package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server productionServer = new Server();
        productionServer.setUrl("https://9165.408procr.amypo.ai");
        productionServer.setDescription("Production Server");

        return new OpenAPI()
                .servers(List.of(productionServer))
                .info(new Info()
                        .title("Demo API")
                        .description("Spring Boot REST API Documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Demo Team")
                                .email("support@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
