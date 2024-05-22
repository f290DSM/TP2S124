package com.example.springbootplayground.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("titulo", "Spring Boot Playground");
        model.addAttribute("conteudo", "Mussum ipsum cacildis vidis litrus abertis.");
        return "index";
    }

    @GetMapping("/soma/{a}/{b}")
    public String soma(@PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        System.out.println(a);
        System.out.println(b);
        System.out.printf("%s + %s  = %s\n", a, b, (a + b));
        return "index";
    }
}
