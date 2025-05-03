package com.gabrielle.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; 
    }

    @GetMapping("/cadastrar")
    public String register() {
        return "cadastrar"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/pag/homeAdmin")
    public String homeAdmin() {
        return "homeAdmin"; 
    }

    @GetMapping("/pag/homeUser")
    public String homeUser() {
        return "homeUser"; 
    }
}
