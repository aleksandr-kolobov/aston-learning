package main.controllers;

import main.model.Teacher;
import main.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("teachers", service.findAll());
        return "teachers";
    }

    @GetMapping("{id}")
    public String showOne(Model model, @PathVariable("id") int id) {
        Optional<Teacher> optionalTeacher = service.findById(id);
        model.addAttribute("teacher", optionalTeacher.isPresent() ?
                optionalTeacher.get() : new Teacher());
        return "teacher";
    }
    @PostMapping()
    public String add(@ModelAttribute("teacher") @Valid Teacher teacher) {
        service.save(teacher);
        return "redirect:/teachers";
    }

    @PutMapping()
    public String update(@ModelAttribute("teacher") @Valid Teacher teacher) {
        service.update(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping()
    public String delete(@ModelAttribute("id") int id) {
        service.delete(id);
        return "redirect:/teachers";
    }

}
