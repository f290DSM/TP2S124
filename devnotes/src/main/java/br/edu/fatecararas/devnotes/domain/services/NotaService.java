package br.edu.fatecararas.devnotes.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import br.edu.fatecararas.devnotes.domain.dto.NotaDTO;
import br.edu.fatecararas.devnotes.domain.entities.Nota;
import br.edu.fatecararas.devnotes.domain.repositories.NotaRepository;

@Service
public class NotaService {

    final NotaRepository repository;
    final ModelMapper mapper;

    public NotaService(NotaRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    public NotaDTO criar(NotaDTO dto) {
        dto.setId(null);
        Nota notaEntity = mapper.map(dto, Nota.class);
        Nota nota = repository.save(notaEntity);

        return mapper.map(nota, NotaDTO.class);
    }

    public List<NotaDTO> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(n -> mapper.map(n, NotaDTO.class))
                .collect(Collectors.toList());
    }

}
