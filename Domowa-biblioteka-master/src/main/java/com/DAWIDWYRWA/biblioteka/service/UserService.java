package com.DAWIDWYRWA.biblioteka.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.DAWIDWYRWA.biblioteka.dto.UserRegistrationDto;
import com.DAWIDWYRWA.biblioteka.model.User;

public interface UserService extends UserDetailsService{

	
	User save(UserRegistrationDto registration);

	User findByUsername(String username);

}
