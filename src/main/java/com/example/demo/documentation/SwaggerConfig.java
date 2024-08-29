package com.example.demo.documentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;



    @OpenAPIDefinition(
            info = @Info(
                    title = "API CATEGORY",
                    description = "Our application creates a category",
                    version = "1.0.0",
                    contact = @Contact(
                            name = "Jesús Torres",
                            email = "jedatoos@gmail.com"
                    ),
                    license = @License(
                            name = "Standard Software Use License for Pragma"
                    )
            ),
            servers = {
                    @Server(
                            description = "DEV SERVER",
                            url = "http://localhost:8080/"
                    )
            }
    )

    public class SwaggerConfig {

    }

