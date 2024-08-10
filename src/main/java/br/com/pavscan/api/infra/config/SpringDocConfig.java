package br.com.pavscan.api.infra.config;

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
                        .title("Cifii - Message API")
                        .description("Simple API to send a message and receive a message")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("N/A"))
                )
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080/").description("Base URL")
                ))
                .tags(Arrays.asList(
                        new Tag().name("Message").description("Operations with a message"),
                        new Tag().name("User").description("Operations with user"),
                        new Tag().name("Authentication").description("Login on the application to get a access token")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        )
                        .addSchemas("MessageDto", new Schema<>()
                                .type("object")
                                .addProperty("message", new Schema<>().type("string"))
                                .addProperty("description", new Schema<>().type("string"))
                        )
                        .addSchemas("MessageDtoList", new ArraySchema()
                                .items(new Schema<>().$ref("#/components/schemas/MessageDto"))
                        )
                        .addSchemas("UserDto", new Schema<>()
                                .type("object")
                                .addProperty("login", new Schema<>().type("string"))
                                .addProperty("password", new Schema<>().type("string"))
                        )
                        .addSchemas("UserDtoList", new ArraySchema()
                                .items(new Schema<>().$ref("#/components/schemas/UserDto"))
                        )
                        .addSchemas("TokenDto", new Schema<>()
                                .type("object")
                                .addProperty("token", new Schema<>().type("string"))
                        )
                        .addSchemas("AuthenticationDto", new Schema<>()
                                .type("object")
                                .addProperty("login", new Schema<>().type("string"))
                                .addProperty("password", new Schema<>().type("string"))
                        )
                        .addResponses("Message200Response", new ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/MessageDto"))
                                        )
                                )
                        )
                        .addResponses("Message201Response", new ApiResponse()
                                .description("Created")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/MessageDto"))
                                        )
                                )
                        )
                        .addResponses("User200Response", new ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/UserDto"))
                                        )
                                )
                        )
                        .addResponses("UserList200Response", new ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/UserDtoList"))
                                        )
                                )
                        )
                        .addResponses("User201Response", new ApiResponse()
                                .description("Created")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/UserDto"))
                                        )
                                )
                        )
                        .addResponses("Authentication200Response", new ApiResponse()
                                .description("Successful operation")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType()
                                                        .schema(new Schema<>().$ref("#/components/schemas/AuthenticationDto"))
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
