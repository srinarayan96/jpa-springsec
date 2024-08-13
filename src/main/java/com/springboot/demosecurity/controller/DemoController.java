package com.springboot.demosecurity.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    @Secured("ROLE_EMPLOYEE")
    public String showHome(Model model){
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("username = "+userDetails.getUsername());
        System.out.println("authorities="+userDetails.getAuthorities());
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("roles", userDetails.getAuthorities());
        return "home";
    }

    @GetMapping("/leaders")
    @Secured("ROLE_MANAGER")
    public String showLeaders(){
        return "leaders";
    }

    @GetMapping("/systems")
    @Secured("ROLE_ADMIN")
    public String showSystems(){
        return "systems";
    }
    @GetMapping("/role-manage")
    public String manageRole(){
        return "role-manager";
    }
}










































