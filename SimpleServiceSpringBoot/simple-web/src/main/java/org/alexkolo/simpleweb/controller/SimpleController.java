package org.alexkolo.simpleweb.controller;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/examples")
public class SimpleController {

    @Data
    @AllArgsConstructor
    class User {
        private String name;
        private String email;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("name", "Ivanov Ivan");

        List<String> departments = Arrays.asList("South", "Center", "West");
        model.addAttribute("departments", departments);

        User user = new User("Ivan", "ivan1234@inbox.ru");
        model.addAttribute("user", user);

        return "examples/index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user) {
        System.out.println("Saving user: " + user);

        return "redirect:/examples";
    }

    @GetMapping("/footer")
    public String footer() {
        return "/examples/fragments/footer :: footer";
    }

    @GetMapping("/header")
    public String header() {
        return "/examples/fragments/header :: header";
    }

}
