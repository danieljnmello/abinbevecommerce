package br.com.abinbev.ecommerce.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.abinbev.ecommerce.resource"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API para teste AbInbev",
				"API para teste AbInbev",
				"Versão 1.0",
				"https://www.udemy.com/terms",
				new Contact("Daniel Mello", "xpto.com", "danieljnmello@gmail.com"),
				"Somente para teste", "https://www.xpto.com", Collections.emptyList() // Vendor
																										// Extensions
		);
	}
}
