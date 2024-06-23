package com.winfred.springbootblog;

import com.winfred.springbootblog.model.Role;
import com.winfred.springbootblog.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title ="Spring Boot Blog App Rest APIs",
        description = "Spring Boot Blog App Rest APIs Documentation",
        version = "v1.0",
        contact = @Contact(
                name = "Winfred",
                email = "newiachibs@gmail.com"
),
        license = @License(
                name = "Apache 2.0",
                url = "https://www/javaguides.net/license"
        )
),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Blog App Documentation",
                url = "https://github.com/Winfred-Achieng/springboot-blog-rest-api"
        )
)

public class SpringbootBlogRestApiApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    public RoleRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        repository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        repository.save(userRole);

    }
}
