package main.controllers;

import main.model.Course;
import main.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @GetMapping("{id}")
    public String showOneCourse(Model model, @PathVariable("id") int id) {
        Optional<Course> optionalCourse = courseService.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            model.addAttribute("course", course);
            model.addAttribute("teachers", course.getTeachers());
            System.out.println(course.getTeachers().size());
        }
        else {
            model.addAttribute("course",  new Course());
            model.addAttribute("teachers", new ArrayList<>());
        }
        return "course";
    }
    @PostMapping()
    public String addCourse(@ModelAttribute("course") @Valid Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }

    @PutMapping()
    public String updateCourse(@ModelAttribute("course") @Valid Course course) {
        courseService.update(course);
        return "redirect:/courses";
    }

    @DeleteMapping()
    public String deleteCourse(@ModelAttribute("id") int id) {
        courseService.delete(id);
        return "redirect:/courses";
    }

}
