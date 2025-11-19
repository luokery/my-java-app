package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

    private final String name;

    public HelloworldController(@Value("${NAME:World}") String name) {
        this.name = name;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello " + name + "!";
    }
}