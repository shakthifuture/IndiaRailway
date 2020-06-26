package com.copper.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.copper.model.User;
import com.copper.repository.UserRepository;
import com.copper.service.dto.UserDto;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	private static final String USER_NAME = "john";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "john";
	private static final String LAST_NAME = "r";
	private static final String EMAIL_ADDRESS = "john@test.com";
	
	@Test
    @Transactional
    public void testAuthorizeSuccess() throws Exception {
		User user = createUserEntity();
		user.setActivated(true);
        userRepository.saveAndFlush(user);
        
        UserDto login = new UserDto();
        login.setUsername(USER_NAME);
        login.setPassword(PASSWORD);
        mockMvc.perform(post("/api/user/authenticate")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.token").isNotEmpty());
	}
	
	@Test
    @Transactional
    public void testAuthorizeNotActivated() throws Exception {
		User user = createUserEntity();
        userRepository.saveAndFlush(user);
        
        UserDto login = new UserDto();
        login.setUsername(USER_NAME);
        login.setPassword(PASSWORD);
        mockMvc.perform(post("/api/user/authenticate")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(login)))
                .andExpect(status().isLocked());
	}
	
	@Test
    @Transactional
    public void testAuthorizeFails() throws Exception {       
        UserDto login = new UserDto();
        login.setUsername("wrong-username");
        login.setPassword("wrong-password");
        mockMvc.perform(post("/api/user/authenticate")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(login)))
                .andExpect(status().isBadRequest());
	}
	
	@Test
    @Transactional
    public void registerUser() throws Exception {
		UserDto user = new UserDto();
        user.setUsername(USER_NAME);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmailAddress(EMAIL_ADDRESS);
        user.setPassword(PASSWORD);
        
        int databaseSizeBeforeCreate = userRepository.findAll().size();
        
        mockMvc.perform(post("/api/user/register")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").isNotEmpty());
        
        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate+1);
	}
	
	private User createUserEntity() {
		User user = new User();
		user.setUsername(USER_NAME);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmailAddres(EMAIL_ADDRESS);
        user.setPassword(passwordEncoder.encode(PASSWORD));
        return user;
	}
	
}
