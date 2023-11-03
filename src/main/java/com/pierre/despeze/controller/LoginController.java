package com.pierre.despeze.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.impl.UserDaoImpl;
import com.pierre.despeze.service.UserService;
import com.pierre.despeze.util.MD5Encryption;

@Controller
public class LoginController {

	private static final String VIEW_LOGIN = "login";
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/login")
	public String showLogin(){

		return VIEW_LOGIN;
	}
	
    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password) {
        
    	password = MD5Encryption.encryptStringToMD5(password);
    	
    	User user = userService.findByEmail(email);
    	log.info("Senha BCrypt: " + password + " Senha MySQL: " + user.getPassword());
    	
        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
        	
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }
	
}