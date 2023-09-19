package com.pierre.despeze.persistence.dao.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pierre.despeze.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setId( rs.getLong("IDUSER") );
		user.setFirstName( rs.getString("FIRSTNAME") );
		user.setLastName( rs.getString("LASTNAME") );
		user.setEmail( rs.getString("EMAIL") );
		user.setPassword( rs.getString("PASSWORD") );
		user.setBirthDate( rs.getDate("BIRTHDATE") );
		user.setGender( rs.getString("GENDER").charAt(0) );
		
		return user;
	}

}
