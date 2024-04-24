package model.dao;

import java.util.List;
import model.Usuario;

public interface IUsuarioDAO {
    void salvar(Usuario usuario) throws Exception;

    Usuario buscarPorEmail(String email);

    void atualizar(Usuario usuario);

    void exluir(Integer id);

    List<Usuario> buscarTodos();
}
