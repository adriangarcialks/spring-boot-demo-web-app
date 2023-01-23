package com.example.demo.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class StudentSeeder {

	@Bean
	CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
		return args -> {
			List<User> randomUsers = getRandomUsers();
			for (int i = 0; i < randomUsers.size(); i++) {
				Student student = new Student(i+1L, randomUsers.get(i).getFirst_name(), randomUsers.get(i).getEmail(), randomUsers.get(i).getDate_of_birth());
				repository.save(student);
			}
		};
	}

	public List<User> getRandomUsers() {
		String uri = "https://random-data-api.com/api/v2/users?size=5";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<User>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		
		List<User> result = response.getBody();
		return result;
	}

}
