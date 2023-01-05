package com.backEndRestApi.Config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;

import io.jsonwebtoken.lang.Collections;
import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//public class SwaggerConfig {
//	
//	public static final String AUTHORIZATION_HADER="Authorization";
//	
//	private ApiKey apiKeys() {
//		return new ApiKey("JWT", AUTHORIZATION_HADER, "header");
//	}
//	
//	private List<SecurityContext> securityContext(){
//		 return SecurityContext.
//	}
//	
//	@Bean
//	public Docket api() {
//		
//		
//		// we providiong all paths into Swagger
//		return new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(gtInfo())
//				.securityContexts()
//				.securitySchemes(Arrays.asList(apiKeys()))
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//		
//	}
//
//	private ApiInfo gtInfo() {
//		// TODO Auto-generated method stub
//		return new ApiInfo("BaKEndRestApiApplication : Swagger", "This is developed by Prasannakumar Dhavande", null, null, null, null, null);
//		
//	}
//
//}
