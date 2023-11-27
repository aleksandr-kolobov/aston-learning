package main.controllers;

import main.model.Course;
import main.model.Student;
import main.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @GetMapping("{id}")
    public String showOneStudent(Model model, @PathVariable("id") int id) {
        Optional<Student> optionalStudent = studentService.findById(id);
        model.addAttribute("student", optionalStudent.isPresent() ?
                optionalStudent.get() : new Course());
        return "student";
    }
    @PostMapping()
    public String addStudent(@ModelAttribute("student") @Valid Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @PutMapping()
    public String updateStudent(@ModelAttribute("student") @Valid Student student) {
        studentService.update(student);
        return "redirect:/students";
    }

    @DeleteMapping()
    public String deleteStudent(@ModelAttribute("id") int id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
