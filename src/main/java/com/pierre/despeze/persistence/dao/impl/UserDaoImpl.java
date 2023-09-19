package com.pierre.despeze.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.pierre.despeze.exception.InternalErrorException;
import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.UserDao;
import com.pierre.despeze.persistence.dao.mapping.UserRowMapper;

@Service
public class UserDaoImpl implements UserDao{

	@Autowired
    private JdbcTemplate jdbc;
    
	@Override
	public List<User> getUserList() {
		
		String query = "SELECT * FROM DESPEZE.REG_USERS";
		
		List<User> userList = new ArrayList<>();
		
		try {
			
			userList.addAll(
					jdbc.query(query, new UserRowMapper())
			);
			
		} catch( EmptyResultDataAccessException error) {
			
			return userList;
		
		} catch( Exception error) {
			
			throw new InternalErrorException(error);
		}
		
		return null;
	}

}
