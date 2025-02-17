package az.portfoliomanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Movie API",
                version = "1.0",
                description = "Movie API Documentation",
                contact = @Contact(
                        name = "Mrrufat",
                        email = "ruafyseyidov@gmail.com"
                )
        )
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components().addSecuritySchemes("BearerAuth",
                        new SecurityScheme()
                                .name("BearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Movie API")
                        .version("1.0")
                        .description("API for managing movies")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
