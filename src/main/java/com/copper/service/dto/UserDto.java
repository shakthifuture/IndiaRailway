package com.copper.service.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.copper.model.Role;
import com.copper.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Integer id;
	private String password;
	private String username;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String token;
	private boolean activated;
	private Set<String> roles;
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.emailAddress = user.getEmailAddres();
		this.activated = user.isActivated();
		this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
	}
	
}
