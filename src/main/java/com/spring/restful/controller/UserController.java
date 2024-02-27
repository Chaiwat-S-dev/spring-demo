package com.spring.restful.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.restful.model.User;
import com.spring.restful.service.UserService;

@Controller
public class UserController {
    @Autowired
    private final UserService userService;
   
    UserController(UserService userService) {
		this.userService = userService;
	}
    
    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.retrieveUser());
        return "user-list";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        System.out.println(id);
        User user =  userService.retrieveUser(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        System.out.printf("user info: " + user.toString() + "%n");
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.retrieveUser(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }
}

