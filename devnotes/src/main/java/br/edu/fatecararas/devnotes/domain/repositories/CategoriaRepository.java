package br.edu.fatecararas.devnotes.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecararas.devnotes.domain.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
