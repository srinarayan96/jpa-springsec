package com.springboot.demosecurity.controller;

import com.springboot.demosecurity.entity.Roles;
import com.springboot.demosecurity.models.RolesInput;
import com.springboot.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/find-user")
    @Secured("ROLE_ADMIN")
    public String findUserRoles(@RequestParam("username") String username, Model model){
        List<String> roles = userService.findUsersRoles(username).stream().map(Roles::getRole).toList();
        model.addAttribute("userId", username);
        model.addAttribute("roles", roles);
        model.addAttribute("addedRoles", new RolesInput());
        model.addAttribute("removedRoles", new RolesInput());
        return "role-manager";
    }

    @PostMapping("/add-roles/{userId}")
    @Secured("ROLE_ADMIN")
    public String addRoles(@PathVariable("userId") String username,
                           @ModelAttribute("addedRoles") RolesInput addRolesList){
        userService.addRoles(username, addRolesList);
        return "redirect:/find-user?username="+username;
    }

    @PostMapping("/remove-roles/{userId}")
    @Secured("ROLE_ADMIN")
    public String removeRoles(@PathVariable("userId") String username,
                              @ModelAttribute("removedRoles") RolesInput removeRolesList){
        userService.removeRoles(username, removeRolesList);
        return "redirect:/find-user?username="+username;
    }
}
