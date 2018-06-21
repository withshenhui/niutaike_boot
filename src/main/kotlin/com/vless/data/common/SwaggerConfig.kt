package com.vless.data.common

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.ApiInfoBuilder

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun controllerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("NewTech Admin API")
                .apiInfo(ApiInfoBuilder()
                        .title("标题：NewTech API DOC")
                        .description("描述：NewTech API DOC")
                        .contact("Peter SHEN")
                        .license("MIT")
                        .licenseUrl("https://mit-license.org")
                        .termsOfServiceUrl("http://www.newtecharmor.com")
                        .version("V1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vless.data.content.controller"))
                .paths(PathSelectors.any()).build()


    }
}