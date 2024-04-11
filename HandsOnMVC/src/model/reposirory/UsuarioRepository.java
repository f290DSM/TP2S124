package model.reposirory;

import java.util.List;

import model.Usuario;

public interface UsuarioRepository {
    Usuario criar(Usuario usuario);

    List<Usuario> obterTodos();

    Usuario atualizar(Usuario usuario);

    void excluir(Integer id);
}
