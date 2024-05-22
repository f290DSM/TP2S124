package br.edu.fatecararas.springplayground.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.fatecararas.springplayground.model.entities.NoteEntity;
import br.edu.fatecararas.springplayground.model.repositories.NotesRepository;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesRepository repository;    
    
    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public String getNotes(Model model) {
        List<NoteEntity> notes = repository.findAll();
        model.addAttribute("notes", notes);
        return "notes";
    }
}
