package com.pierre.despeze.persistence.dao;

import java.util.List;

import com.pierre.despeze.model.User;

public interface UserDao {

	public List<User> getUserList();
}