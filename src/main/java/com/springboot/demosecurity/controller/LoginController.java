package com.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "fancy-login";
    }
    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }
}
