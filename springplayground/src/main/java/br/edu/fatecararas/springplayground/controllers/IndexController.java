package br.edu.fatecararas.springplayground.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("titulo", "Spring Boot Home Page");
        model.addAttribute("conteudo", "Mussum ipsum cacildis vidis litrus abertis");
        return "home";
    }
}
