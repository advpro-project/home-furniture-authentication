package com.hoomgroom.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/auth")
public class WebController {

    @GetMapping("")
    public String authPage(Model model){
        return "authPage";
    }
}