package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	// AUTHENTICATION
	@Bean
	@Autowired
	protected InMemoryUserDetailsManager configureAuthentication(BCryptPasswordEncoder encoder) {
		
		List<UserDetails> userDetails = new ArrayList<>();
		
		List<GrantedAuthority> userRoles = new ArrayList<>();
		userRoles.add(new SimpleGrantedAuthority("USER"));

		List<GrantedAuthority> adminRoles = new ArrayList<>();
		adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
		
		userDetails.add(new User("user",
				encoder.encode("LKS.2023"),
				userRoles));
		userDetails.add(new User("admin",
				encoder.encode("LKS.2023"),
				adminRoles));	
		
		return new InMemoryUserDetailsManager(userDetails);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	// AUTHORIZATION
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/students/add/**").hasAuthority("ADMIN")
		.requestMatchers("/students/update/**").hasAuthority("ADMIN")
		.requestMatchers("/students/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/students").hasAnyAuthority("USER","ADMIN")
		.requestMatchers("/").permitAll()
		.requestMatchers("/css/*").permitAll()
		.requestMatchers("/logout").permitAll()
		.and().formLogin();
		
		return http.build();
    }
	
}
