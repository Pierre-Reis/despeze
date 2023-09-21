package com.pierre.despeze.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.UserDao;
import com.pierre.despeze.persistence.dao.impl.UserDaoImpl;
import com.pierre.despeze.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUserList() {
		
		log.info(userDao.getUserList());
		
		return userDao.getUserList();
	}

}
