package com.spring.auth.anotations.components.controllers;

import io.swagger.annotations.Api;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@Api(tags = "Users", description = "Users endpoints")
@RequestMapping("${api.prefix}/users")
public @interface UserController {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
