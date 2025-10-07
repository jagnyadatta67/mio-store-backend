package  com.miostore.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "MiO Store API",
                version = "1.0",
                description = "MiO Millet-based E-Commerce API Documentation"
        )
)
public class OpenApiConfig {
}