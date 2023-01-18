package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
		return args -> {
			Student maria = new Student(1L, "Maria", "maria@gmail.com", LocalDate.of(1999, Month.JANUARY, 5));
			Student alex = new Student(2L, "Alex", "alex@gmail.com", LocalDate.of(1997, Month.FEBRUARY, 12));
			Student jose = new Student(3L, "Jose", "jose@gmail.com", LocalDate.of(2000, Month.AUGUST, 1));
			Student laura = new Student(4L, "Laura", "laura@gmail.com", LocalDate.of(1998, Month.DECEMBER, 11));
			Student marcos = new Student(5L, "Marcos", "marcos@gmail.com", LocalDate.of(2001, Month.MAY, 31));

			List<Student> students = Arrays.asList(maria, alex, jose, laura, marcos);
			repository.saveAll(students);
		};
	}
	
}
