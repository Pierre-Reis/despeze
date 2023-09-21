package com.pierre.despeze.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.impl.UserDaoImpl;
import com.pierre.despeze.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String VIEW_LISTA_USERS = "userList";
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/")
	public String showUserList( Model model ) throws ParseException {
		
		List<User> users = userService.getUserList();
		model.addAttribute("users", users);
		
		log.info(users);
		
		return VIEW_LISTA_USERS;
	}
}
