package com.copper.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.copper.service.UserService;
import com.copper.service.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.newUser(userDto));
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) {
		try {
			userDto = userService.authenticateUser(userDto);
			if(userDto.isActivated())
				return ResponseEntity.ok(userDto);
			else {
				log.debug("User not activated: "+userDto.getUsername());
				return ResponseEntity.status(HttpStatus.LOCKED).body("Not activated");
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
