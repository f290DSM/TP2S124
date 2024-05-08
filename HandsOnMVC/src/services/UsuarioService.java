package services;

import java.util.List;
import java.util.Objects;
import java.util.logging.*;

import model.Usuario;
import model.reposirory.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;
    private final Logger logger;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
        this.logger = Logger.getLogger(UsuarioService.class.getName());
    }

    public void criar(Usuario usuario) {
        try {
            validar(usuario, false);
            repository.criar(usuario);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Falha ao salvar usuário", e);
        }
    }

    public List<Usuario> obterTodos() {
        return repository.obterTodos();
    }

    public Usuario atualizar(Usuario usuario) {
        validar(usuario, true);
        return repository.atualizar(usuario);
    }

    public void excluir(Integer id) {
        try {
        validarId(id);
        repository.excluir(id);
    }

    private void validarId(Integer id) {
        if (Objects.isNull(id))
            throw new RuntimeException("Id não pode ser nulo");
    }

    private void validar(Usuario usuario, boolean validarId) {
        if (Objects.isNull(usuario)) {
            throw new RuntimeException("Usuário deve ser inicializado.");
        }
        if (validarId) {
            validarId(usuario.getId());
        }

        if (usuario.getNome().isEmpty() || usuario.getNome().length() < 2) {
            throw new RuntimeException("Nome de usuário inválido.");
        }

        if (usuario.getSenha().isEmpty() || usuario.getSenha().length() < 4) {
            throw new RuntimeException("Senha de usuário inválida.");
        }

        if (usuario.getEmail().isEmpty()
                || usuario.getEmail().length() < 5
                || !usuario.getEmail().contains("@")) {
            throw new RuntimeException("E-mail inválido.");
        }
    }
}
