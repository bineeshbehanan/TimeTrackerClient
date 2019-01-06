package com.userdata.app.controllers;

import java.io.IOException;
import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.userdata.app.entities.User;
import com.userdata.app.service.UserService;

@Controller
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) throws ParseException, IOException {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userService.saveUser(user);
        model.addAttribute("users", userService.getUsers(user));
        return "index";
    }
    
    @GetMapping("/search")
    public String showSearchForm(User user,  Model model) {
    	return "search-user";
    }
    @PostMapping("/search-user")
    public String searchUser(@Valid User user, BindingResult result, Model model) throws IOException, ParseException {
        if (result.hasErrors()) {
            return "search-user";
        }
        
        model.addAttribute("users", userService.getUsers(user));
        return "index";
    }

}
