package com.spring.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.restful.model.User;
import com.spring.restful.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {

	@Autowired
	private final UserService userService;

	UserApiController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users/{id}")
	public User one(@PathVariable Long id) {

		return userService.retrieveUser(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	}
}