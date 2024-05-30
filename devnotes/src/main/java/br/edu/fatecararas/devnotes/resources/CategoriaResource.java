package br.edu.fatecararas.devnotes.resources;

import java.net.URI;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.fatecararas.devnotes.domain.dto.CategoriaDTO;
import br.edu.fatecararas.devnotes.domain.services.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/todas")
    public List<CategoriaDTO> buscarCategorias() {
        return service.buscarTodas();
    }

    @PostMapping("/categoria")
    public ResponseEntity<Void> criar(@RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = service.criar(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/categoria/{id}")
    public void excluir(@PathVariable("id") Long id) {
        service.excluir(id);
    }
}

/**
 * Requisicao no ThunderClient
 * http://localhost:9000/api/v1/categorias/todas
 */

 /**
  * 
bit.ly/3R7avBV
  */
