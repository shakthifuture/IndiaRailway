package com.copper.service.mapper;

import org.springframework.stereotype.Service;

import com.copper.model.User;
import com.copper.service.dto.UserDto;

@Service
public class UserMapper implements EntityMapper<UserDto, User> {
	
	@Override
	public UserDto toDto(User user) {
		return new UserDto(user);
	}

	@Override
	public User toEntity(UserDto dto) {
		if(dto == null)
			return null;
		else {
			User user = new User();
			user.setId(dto.getId());
			user.setUsername(dto.getUsername());
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setEmailAddres(dto.getEmailAddress());
			return user;
		}
	}

}
