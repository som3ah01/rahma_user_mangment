/*package com.taskMangament.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2).groupName("Auth").select()
//                .apis(RequestHandlerSelectors.basePackage("com.momknswitch.service.auth.controller"))
//                .paths(any()).build().apiInfo(new ApiInfo("Auth Service",
//                        "A service to provide data access to Accounts", "1.0.0", null,
//                        null,null, null));                                       
//    }

    @Value("${info.app.name:ServiceTitle}")
    private String title;

    @Value("${info.app.description:ServiceDescription}")
    private String description;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license("")
                .licenseUrl("http://unlicense.org")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", ""))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.myapp.api"))
                .apis(RequestHandlerSelectors.basePackage("com.momknswitch.service.auth.controller"))
                .build()
                .apiInfo(apiInfo());
    }
}
*/