package com.example.demo.config;

import java.time.LocalDate;

public class User {
	
	private String first_name;
	private LocalDate date_of_birth;
	private String email;
	
	public User() {
	}
	
	public User(String first_name, LocalDate date_of_birth, String email) {
		this.first_name = first_name;
		this.date_of_birth = date_of_birth;
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [first_name=" + first_name + ", date_of_birth=" + date_of_birth + ", email=" + email + "]";
	}
	
}
