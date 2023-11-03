package com.pierre.despeze.persistence.dao;

import java.util.List;

import com.pierre.despeze.model.User;

public interface UserDao {

	public User findByEmail(String email);
	
	public List<User> getUserList();
}