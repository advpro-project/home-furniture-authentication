package com.hoomgroom.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class WebController {
    @GetMapping
    public String homePage() {
        return "HelloWorld";
    }
}