package com.pierre.despeze.controller;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pierre.despeze.persistence.dao.impl.UserDaoImpl;

@Controller
@RequestMapping("/bank-account")
public class BankAccountController {

	private static final String VIEW_LIST_BANK_ACCOUNTS = "bank-account-list";
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );
	
	@GetMapping(value="/")
	public String showUserList( Model model ) throws ParseException {
		
		return VIEW_LIST_BANK_ACCOUNTS;
	}
}
