package com.springboot.demosecurity.controller;

import com.springboot.demosecurity.models.WebUser;
import com.springboot.demosecurity.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "fancy-login";
    }
    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }
    @PostMapping("/access-denied")
    public String showAccessDeniedPagePost(){
        return "access-denied";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("webUser", new WebUser());
        return "register";
    }

    @PostMapping("/register/user")
    public String registerUser(@Valid @ModelAttribute("webUser")WebUser webUser, BindingResult bindingResult,
                               HttpSession session, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("registrationError", Objects.requireNonNull(bindingResult
                    .getFieldError()).getDefaultMessage() );
            return "register";
        }
        String result = userService.saveUser(webUser);
        if(result.equalsIgnoreCase("already exists")){
            model.addAttribute("registrationError", "User already exists");
            return "register";
        }
        if(result.equalsIgnoreCase("User registered Successfully")){
            model.addAttribute("registerSuccess", "User registered successfully");
            //return new ModelAndView("redirect:/redirectedUrl", model);
            return "fancy-login";
        }
        return "register";
    }
}
