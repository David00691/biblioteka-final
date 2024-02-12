package com.DAWIDWYRWA.biblioteka.dto;

import javax.validation.constraints.NotEmpty;

import com.DAWIDWYRWA.biblioteka.matcher.FieldMatch;


@FieldMatch.List({
	
	@FieldMatch(first = "password", second = "confirmPassword", message = "Hasła muszą być zgodne")
	
})

public class UserRegistrationDto {
	
	@NotEmpty( message = "Pole nie może być puste" )
	private String username;
	
	@NotEmpty( message = "Pole nie może być puste" )
	private String password;
	
	@NotEmpty( message = "Pole nie może być puste" )
	private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	

}
