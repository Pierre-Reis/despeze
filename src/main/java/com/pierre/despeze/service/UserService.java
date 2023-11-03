package com.pierre.despeze.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pierre.despeze.model.User;

@Service
public interface UserService {

	public User findByEmail(String email);
	
	public List<User> getUserList();
}