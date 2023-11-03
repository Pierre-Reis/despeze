package com.pierre.despeze.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pierre.despeze.persistence.dao.impl.UserDaoImpl;

@Controller
public class DashboardController {

	private static final String VIEW_DASHBOARD = "dashboard";
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );
	
	@GetMapping(value="/dashboard")
	public String showDashboard(){

		return VIEW_DASHBOARD;
	}
}
