package com.DAWIDWYRWA.biblioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DAWIDWYRWA.biblioteka.model.User;

public interface UserRepository extends JpaRepository <User, Integer> {
	
	User findByUsername(String username);
	
}
