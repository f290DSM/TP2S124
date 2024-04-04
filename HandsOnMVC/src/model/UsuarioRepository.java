package model;

import java.util.List;

public interface UsuarioRepository {
    Usuario criar(Usuario usuario);

    List<Usuario> obterTodos();

    Usuario atualizar(Usuario usuario);

    void excluir(Integer id);
}
