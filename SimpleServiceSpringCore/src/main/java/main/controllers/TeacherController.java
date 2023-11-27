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

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "teachers";
    }

    @GetMapping("{id}")
    public String showOneTeacher(Model model, @PathVariable("id") int id) {
        Optional<Teacher> optionalTeacher = teacherService.findById(id);
        model.addAttribute("teacher", optionalTeacher.isPresent() ?
                optionalTeacher.get() : new Teacher());
        return "teacher";
    }
    @PostMapping()
    public String addTeacher(@ModelAttribute("teacher") @Valid Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @PutMapping()
    public String updateTeacher(@ModelAttribute("teacher") @Valid Teacher teacher) {
        teacherService.update(teacher);
        return "redirect:/teachers";
    }

    @DeleteMapping()
    public String delete(@ModelAttribute("id") int id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }

}
