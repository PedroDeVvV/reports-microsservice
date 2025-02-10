package com.tracevia.app.infra.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for setting up OpenAPI documentation for the Report Service API.
 * This class uses the {@link OpenAPIDefinition} annotation to define metadata such as the API title, version,
 * and description for the generated OpenAPI documentation.
 *
 * The API documentation provides details about the available endpoints and their functionality in the Report Service API.
 */
@Configuration
@OpenAPIDefinition(info =
@Info(title = "Report Service API",
        version = "v1",
        description = "Documentation of Report Service API"))
public class OpenApiConfiguration {

    /**
     * Configures and returns a custom OpenAPI instance.
     * This method sets up the OpenAPI documentation for the API, including metadata
     * such as the title, version, and description.
     *
     * @return A custom OpenAPI object containing the API documentation configuration.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(
                new io.swagger.v3.oas.models.info.Info()
                    .title("Report Service API")
                    .version("v1")
                    .license(
                        new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
            );
    }
}
