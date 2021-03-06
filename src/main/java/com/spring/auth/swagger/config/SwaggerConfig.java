package com.spring.auth.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.time.LocalDate;

/**
 * @author diegotobalina created on 24/06/2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket eDesignApi(SwaggerConfigProperties swaggerConfigProperties) {
        final String basePackage = "com.spring.auth";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swaggerConfigProperties))
                .ignoredParameterTypes(Principal.class)
                .enable(Boolean.parseBoolean(swaggerConfigProperties.getEnabled()))
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(
                        Boolean.parseBoolean(swaggerConfigProperties.getUseDefaultResponseMessages()))
                .enableUrlTemplating(
                        Boolean.parseBoolean(swaggerConfigProperties.getEnableUrlTemplating()));
    }

    @Bean
    UiConfiguration uiConfig(SwaggerConfigProperties swaggerConfigProperties) {
        return UiConfigurationBuilder.builder()
                .deepLinking(Boolean.valueOf(swaggerConfigProperties.getDeepLinking()))
                .displayOperationId(Boolean.valueOf(swaggerConfigProperties.getDisplayOperationId()))
                .defaultModelsExpandDepth(
                        Integer.valueOf(swaggerConfigProperties.getDefaultModelsExpandDepth()))
                .defaultModelExpandDepth(
                        Integer.valueOf(swaggerConfigProperties.getDefaultModelExpandDepth()))
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(
                        Boolean.valueOf(swaggerConfigProperties.getDisplayRequestDuration()))
                .docExpansion(DocExpansion.NONE)
                .filter(Boolean.valueOf(swaggerConfigProperties.getFilter()))
                .maxDisplayedTags(Integer.valueOf(swaggerConfigProperties.getMaxDisplayedTags()))
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(Boolean.valueOf(swaggerConfigProperties.getShowExtensions()))
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {
        return new ApiInfoBuilder()
                .title(swaggerConfigProperties.getTitle())
                .description(swaggerConfigProperties.getDescription())
                .version(swaggerConfigProperties.getApiVersion())
                .build();
    }
}
