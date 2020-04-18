package com.merryba.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig{
	
		@Bean
		public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	         .apiInfo(getApiInfo())
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.merryba.api"))           
	          .paths(PathSelectors.ant("/users/**"))                          
	          .build();                                           
	    }
		
		//Swagger Metadata :http://localhost:8080/v2/api-docs
		//Swagger UI URL http://localhost:8080/swagger-ui.html
		@Bean
	    public LinkDiscoverers discoverers() {
	        List<LinkDiscoverer> plugins = new ArrayList<>();
	        plugins.add(new CollectionJsonLinkDiscoverer());
	        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

	    }
		
		private ApiInfo  getApiInfo() {
			return new ApiInfoBuilder()
					.title("RestAPI User Management Service")
					.description("This page lists all API's of User Management Service")
					.version("2.0")
					.contact(new Contact("Merryba","https://merryba.com","merryba@gmail.com"))
					.license("License 2.0")
					.licenseUrl("https://www.merryba.com/license.html")
					.build();
		}
		
}
