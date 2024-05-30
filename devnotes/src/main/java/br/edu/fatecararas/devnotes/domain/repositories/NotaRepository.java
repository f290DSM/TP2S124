package br.edu.fatecararas.devnotes.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecararas.devnotes.domain.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{
    
}
