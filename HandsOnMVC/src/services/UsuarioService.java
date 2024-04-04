package services;

import java.util.List;
import java.util.Objects;

import model.Usuario;
import model.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criar(Usuario usuario) {
        validar(usuario, false);
        return repository.criar(usuario);
    }

    public List<Usuario> obterTodos() {
        return repository.obterTodos();
    }

    public Usuario atualizar(Usuario usuario) {
        validar(usuario, true);
        return repository.atualizar(usuario);
    }

    public void excluir(Integer id) {
        validarId(id);
        repository.excluir(id);
    }

    private void validarId(Integer id) {
        if (Objects.isNull(id)) throw new RuntimeException("Id não pode ser nulo");
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

        if (usuario.getEmail().isEmpty() || usuario.getEmail().length() < 5 || usuario.getEmail().contains("@")) {
            throw new RuntimeException("E-mail inválido.");
        }
    }
}
