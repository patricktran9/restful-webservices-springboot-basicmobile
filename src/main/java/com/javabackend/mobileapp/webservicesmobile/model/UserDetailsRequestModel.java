package com.javabackend.mobileapp.webservicesmobile.model;

public class UserDetailsRequestModel {

	private String lastName;
	private String firstName;
	private String email;
	private String password;
	public UserDetailsRequestModel() {
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
