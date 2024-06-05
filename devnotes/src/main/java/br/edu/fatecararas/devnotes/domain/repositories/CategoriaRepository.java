package br.edu.fatecararas.devnotes.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fatecararas.devnotes.domain.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = """
        select count(c.id) from categorias c
        inner join notas n on n.id_categoria = c.id
        where c.id = :idCategoria
    """, nativeQuery = true)
    Integer existemNotasAssociadas(Long idCategoria);

}
