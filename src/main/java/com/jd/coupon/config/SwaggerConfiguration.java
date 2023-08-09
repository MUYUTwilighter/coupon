package com.jd.coupon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author MUYU_Twilighter
 */
@Configuration
public class SwaggerConfiguration {
    @Value("${swagger.enabled}")
    Boolean swaggerEnabled;

    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .enable(swaggerEnabled)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.jd.coupon"))
            .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("MUYU_Twilighter", "https://github.com/MUYUTwilighter", "1484605372@qq.com");
        return new ApiInfoBuilder()
            .title("Coupon management backend")
            .contact(contact)
            .build();
    }
}
