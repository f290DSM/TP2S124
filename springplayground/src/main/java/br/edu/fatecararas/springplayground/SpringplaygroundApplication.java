package br.edu.fatecararas.springplayground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.fatecararas.springplayground.model.Note;
import br.edu.fatecararas.springplayground.model.entities.NoteEntity;
import br.edu.fatecararas.springplayground.model.repositories.NotesRepository;

@SpringBootApplication
public class SpringplaygroundApplication implements CommandLineRunner {

	@Autowired
	private NotesRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringplaygroundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		NoteEntity note1 = new NoteEntity("Learn Spring Boot");
        NoteEntity note2 = new NoteEntity("Learn Django");
        List<NoteEntity> notes = Arrays.asList(note1, note2);

		repository.saveAll(notes);
	}

}
