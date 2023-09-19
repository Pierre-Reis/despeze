package com.pierre.despeze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pierre.despeze.model.User;
import com.pierre.despeze.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String VIEW_LISTA_USERS = "userList";
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/")
	public String showUserList( Model model ) {
		
		List<User> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		
		return VIEW_LISTA_USERS;
	}
}
