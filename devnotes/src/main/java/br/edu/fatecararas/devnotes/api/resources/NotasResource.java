package br.edu.fatecararas.devnotes.api.resources;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatecararas.devnotes.api.dto.NotaDTO;
import br.edu.fatecararas.devnotes.domain.services.NotaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notas")
public class NotasResource {

    private final NotaService service;
    
    public NotasResource(NotaService service) {
        this.service = service;
    }

    //TODO: Criar documentação da criação de uma nova nota.
    @PostMapping("/nota")
    public NotaDTO criar(@RequestBody @Valid NotaDTO dto) {
        return service.criar(dto);
    }

    //TODO: Criar documentação da criação de uma nova nota.
    @GetMapping("/todas")
    public List<NotaDTO> buscarTodas() {
        return service.buscarTodas();
    }

    //TODO: Criar documentação do método de exclusão de nota existente.
    @DeleteMapping("/nota/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable("id") Long id) {
        service.apagar(id);
    }

    //TODO: Criar método de busca de nota existen por ID com a documentação.

    //TODO: Criar método de alteração de nota existente com a documentação.
}
