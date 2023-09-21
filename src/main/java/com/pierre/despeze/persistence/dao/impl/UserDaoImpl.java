package com.pierre.despeze.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );

	@Autowired
    private JdbcTemplate jdbc;
    
	@Override
	public List<User> getUserList() {
		
		String query = "SELECT * FROM REG_USERS";
		
		List<User> userList = new ArrayList<>();
		
		try {
			
			log.info( "Query: " + query );
			
			userList.addAll( jdbc.query(query, new UserRowMapper())	);
			
		} catch( EmptyResultDataAccessException error) {
			
			throw new InternalErrorException(error);
		
		} catch( Exception error) {
			
			String message = "Não foi possível carregar a lista de usuários";
			
			log.error( message, error );
			
			throw new InternalErrorException(error);
		}

		return userList;
	}

}
