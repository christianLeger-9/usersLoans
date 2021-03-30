package com.usersLoans.usersLoans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket docket() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.usersLoans.usersLoans.controllers"))
//          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Titulo Swagger").description("Descripción del swagger.").version("V1.0").build();
	}

	private Predicate<String> getPaths() {
		return PathSelectors.regex("/users.*");
		} 
}
