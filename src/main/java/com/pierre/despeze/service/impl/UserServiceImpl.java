package com.pierre.despeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.UserDao;
import com.pierre.despeze.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserList() {

		return userDao.getUserList();
	}

}
