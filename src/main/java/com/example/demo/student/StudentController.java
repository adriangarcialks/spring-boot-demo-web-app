package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public String getStudents(Model model, HttpServletRequest request) {
		model.addAttribute("students", studentService.GetStudents());
		
		String requestUri = request.getRequestURI();
		model.addAttribute("requestUri", requestUri);
		
		return "students";
	}

	@GetMapping("/students/add")
	public String addNewEmployee(Model model, HttpServletRequest request) {
		Student student = new Student();
		model.addAttribute("student", student);
		
		String requestUri = request.getRequestURI();
		model.addAttribute("requestUri", requestUri);
		
		return "add_student";
	}

	@PostMapping("/students/add")
	public String register(@ModelAttribute("student") Student student, Model model) {
		try {
			studentService.addNewStudent(student);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "add_student";
		}
		return "redirect:/students";
	}

	@GetMapping("/students/delete/{studentId}")
	public String deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
		return "redirect:/students";
	}

	@GetMapping("/students/update/{studentId}")
	public String updateStudent(@PathVariable("studentId") Long studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "update_student";
	}

	@PostMapping("/students/update/{studentId}")
	public String save(@PathVariable("studentId") Long studentId, @Validated Student studentUpdate,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			studentUpdate.setId(studentId);
			return "update_student";
		}

		try {
			studentService.updateStudent(studentId, studentUpdate.getName(), studentUpdate.getEmail());
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());

			Student student = studentService.getStudentById(studentId);
			model.addAttribute("student", student);

			return "update_student";
		}

		return "redirect:/students";
	}

}
