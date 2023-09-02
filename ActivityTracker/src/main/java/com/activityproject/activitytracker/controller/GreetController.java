package com.activityproject.activitytracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;


    @Slf4j
    @RestController
    @RequestMapping("api/v1/greet")

    public class GreetController {

        @GetMapping
        public String greet() {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("User {} has role {}", authentication.getName(), authentication.getAuthorities());
            return "hello " + authentication.getName();
        }
}
