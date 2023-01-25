package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@InjectMocks
	StudentService studentService;
	
	@Mock
    StudentRepository dao;

	@Test
	public void testSaveStudent() {
		Student student = new Student(1L, "name", "name@email.com", LocalDate.of(2020, Month.JANUARY, 8));

		studentService.addNewStudent(student);

		verify(dao, times(1)).save(student);
	}

}
