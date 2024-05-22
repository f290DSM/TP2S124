package br.edu.fatecararas.springplayground.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatecararas.springplayground.model.entities.NoteEntity;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {
    
}
