package com.jason07289.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig { // http://localhost:8080/swagger-ui/index.html	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jason07289"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo info() {
		return new ApiInfoBuilder()
				.title("ItemCategory API")
				.description("무신사 채용를 위한 <b>ItemCategory App</b>")
				.license("jason07289")
				.version("1.0")
				.build();
	}
}