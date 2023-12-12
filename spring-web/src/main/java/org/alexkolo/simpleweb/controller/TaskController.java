package org.alexkolo.simpleweb.controller;

import lombok.RequiredArgsConstructor;
import org.alexkolo.simpleweb.model.Task;
import org.alexkolo.simpleweb.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskServiceImpl;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tasks", taskServiceImpl.findAll());

        return "tasks/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());

        return "tasks/create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskServiceImpl.save(task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Task task = taskServiceImpl.findById(id);
        if (task != null) {
            model.addAttribute("task", task);
            return "tasks/edit";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute Task task) {
        taskServiceImpl.update(task);

        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable long id) {
        taskServiceImpl.deleteById(id);

        return "redirect:/tasks";
    }

}