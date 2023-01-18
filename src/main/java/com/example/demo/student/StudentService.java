package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	@Autowired
	private final StudentRepository studentRepository;
	
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> GetStudents() {
		return studentRepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		
		if (student.getName() != null && student.getName() != "" && student.getEmail() != null && student.getEmail() != "" && student.getDob() != null) {
			studentRepository.save(student);
		}
		else {
			throw new IllegalStateException("Incorrect data");
		}
	}
	
	public void deleteStudent(Long studentId) {
		if (!studentRepository.existsById(studentId)) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		
		studentRepository.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
		
		if (name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("email taken"); 
			}
			
			student.setEmail(email);
		}
	}
	
	public Student getStudentById(Long studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));
		return student;
	}
	
}
