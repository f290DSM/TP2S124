package br.edu.fatecararas.devnotes.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fatecararas.devnotes.api.dto.CategoriaDTO;
import br.edu.fatecararas.devnotes.api.exceptions.RegraDeNegocioException;
import br.edu.fatecararas.devnotes.domain.entities.Categoria;
import br.edu.fatecararas.devnotes.domain.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private ModelMapper mapper;

    public List<CategoriaDTO> buscarTodas() {
        return repository.findAll()
                .stream()
                .map(categoria -> mapper.map(categoria, CategoriaDTO.class))
                .collect(Collectors.toList());

    }

    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria entidade = mapper.map(dto, Categoria.class);
        Categoria categoria = repository.save(entidade);
        return mapper.map(categoria, CategoriaDTO.class);
    }

    public void excluir(Long id) {
        Integer existemNotasAssociadas = repository.existemNotasAssociadas(id);

        if (existemNotasAssociadas > 0) {
            throw new RegraDeNegocioException("Existem notas associadas para a categoria com ID: " + id);
        }

        repository.deleteById(id);
    }
}
