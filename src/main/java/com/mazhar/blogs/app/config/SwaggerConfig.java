package com.mazhar.blogs.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {

        return new ApiInfo("Blogging Application : Backend Course",
                "This project is developed by Mazhar Farooque",
                "1.0",
                "Terms of Services",
                new Contact("backend-course",
                        "http://mazhar-farooque/blog.com",
                        "mazharfarooque25@gmail.com"),
                "License of API's",
                "API License", Collections.emptyList());
    }
}
