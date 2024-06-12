package br.edu.fatecararas.devnotes.api.resources;

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

import br.edu.fatecararas.devnotes.api.dto.CategoriaDTO;
import br.edu.fatecararas.devnotes.domain.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Categorias", description =
"Controle de categorias para notas de desenvolvimento.")
@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @Operation(description = "Listar todas categorias cadastradas na base de dados da API.")
    @ApiResponses(value = {
        @ApiResponse(description = "Sucesso na consulta de categorias.", responseCode = "200"),
        @ApiResponse(description = "Não existem categorias cadastradas", responseCode = "404")
    })
    @GetMapping("/todas")
    public List<CategoriaDTO> buscarCategorias() {
        return service.buscarTodas();
    }

    //TODO: Criar a documentação com as anotaões Swagger
    @PostMapping("/categoria")
    public ResponseEntity<Void> criar(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = service.criar(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    //TODO: Criar a documentação com as anotaões Swagger
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/categoria/{id}")
    public void excluir(@PathVariable("id") Long id) {
        service.excluir(id);
    }

    //TODO: Criar o método buscar uma categoria por id, com a documentação.

    //TODO: Criar um método para alterar uma categoria com a documentação.
}
