package com.copper.service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.copper.constants.SecurityConstants;
import com.copper.repository.UserRepository;
import com.copper.service.dto.UserDto;
import com.copper.service.mapper.UserMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder crypt;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		com.copper.model.User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user: "+username);
        }
        return new MyUserDetails(user);
	}
	
	@Transactional(readOnly = true)
	public List<UserDto> getAllUsers(){
		return userRepository.findAll().stream()
				.map(userMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public UserDto newUser(UserDto userDto) {
		userDto.setActivated(false);
		com.copper.model.User user = userMapper.toEntity(userDto);
		user.setPassword(crypt.encode(userDto.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
	}
	
	public UserDto authenticateUser(UserDto userDto) throws UsernameNotFoundException {
		authenticate(userDto.getUsername(), userDto.getPassword());
		com.copper.model.User user = userRepository.findByUsername(userDto.getUsername());
		String token = generateToken(user.getUsername());
		userDto = userMapper.toDto(user);
		userDto.setToken(token);
		userDto.setPassword(null);
		return userDto;
	}
	
	public String generateToken(String username) {
		Claims claims = Jwts.claims().setSubject(username);
		Date exp = new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes());
        return Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512).setExpiration(exp).compact();
	}

	private void authenticate(String username, String password) throws UsernameNotFoundException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UsernameNotFoundException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("INVALID_CREDENTIALS", e);
		}
	}

}
