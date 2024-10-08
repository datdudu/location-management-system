package br.com.locationManagementSystem.api.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Nuven Challenge - Location Management System API")
                        .description("Simple API that represents a Location Management System")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("N/A"))
                )
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080/").description("Base URL")
                ))
                .tags(Arrays.asList(
                        new Tag().name("Location").description("Operations with a location")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        )
                        .addSchemas("LocationDto", new Schema<>()
                                .type("object")
                                .addProperty("name", new Schema<>().type("string"))
                                .addProperty("neighborhood", new Schema<>().type("string"))
                                .addProperty("city", new Schema<>().type("string"))
                                .addProperty("state", new Schema<>().type("string"))
                        )
                        .addResponses("Location200Response", new ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/LocationDto"))
                                        )
                                )
                        )
                        .addResponses("Location201Response", new ApiResponse()
                                .description("Created")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/LocationDto"))
                                        )
                                )
                        )
                        .addResponses("404Response", new ApiResponse()
                                .description("Not found")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().example("Not found"))
                                        )
                                )
                        )
                        .addResponses("204Response", new ApiResponse()
                                .description("NoContent")
                        )
                );
    }
}
