package com.iscte.se.SE10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyController {

    @GetMapping("/some-page")
    public String handleSomePageRequest() {
        return "Hello World!";
    }
}