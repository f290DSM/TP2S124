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

    @PostMapping("/nota")
    public NotaDTO criar(@RequestBody @Valid NotaDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/todas")
    public List<NotaDTO> buscarTodas() {
        return service.buscarTodas();
    }

    @DeleteMapping("/nota/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable("id") Long id) {
        service.apagar(id);
    }
}

/**
 * Marcelo Salvador, Wesley, Maicon, Isabela, Igor, Maria Vitoria, Beatriz, Bruno,
 * Pietra, Frank, Lucas Arantes, Vior Eduardo, Miguel, Mateus Augusto, Lucas Ferreira,
 * Endrew, Fernando, Rafaela, Mateus Luis, Marina, Leandro Wendel, Roberto, Willian,
 * Lucas Assis, Ana Laura, 2 x Barbieri (Gabriel, Miguel)
 */
