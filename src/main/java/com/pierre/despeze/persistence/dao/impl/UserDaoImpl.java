package com.pierre.despeze.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.pierre.despeze.exception.InternalErrorException;
import com.pierre.despeze.model.User;
import com.pierre.despeze.persistence.dao.UserDao;
import com.pierre.despeze.persistence.dao.mapping.UserRowMapper;
import com.pierre.despeze.util.ParamsMap;

@Service
public class UserDaoImpl implements UserDao{
	
	protected static final Log log = LogFactory.getLog( UserDaoImpl.class );

	@Autowired
    private NamedParameterJdbcTemplate jdbc;
    
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

	@Override
	public User findByEmail(String email) {
		
		String query = "SELECT * FROM REG_USERS WHERE EMAIL = :email";
		
		ParamsMap params = new ParamsMap();
		
		params.put("email", email);
		
		User user = null;
		
		try {
			
			log.info( "Query: " + query + " Params: " + params);
			
			user = jdbc.queryForObject(query, params, new UserRowMapper());
			
		} catch( EmptyResultDataAccessException error) {
			
			throw new InternalErrorException(error);
		
		} catch( Exception error) {
			
			String message = "E-mail não cadastrado";
			
			log.error( message, error );
			
			throw new InternalErrorException(error);
		}

		return user;
	}

}
