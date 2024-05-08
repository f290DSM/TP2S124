package model.reposirory;

import java.util.List;

import model.Usuario;
import model.dao.IUsuarioDAO;

public class UsuarioRepositoryMySQLImpl implements UsuarioRepository {

    private final IUsuarioDAO dao;

    public UsuarioRepositoryMySQLImpl(IUsuarioDAO dao) {
        this.dao = dao;
    }

    @Override
    public void criar(Usuario usuario) throws Exception {
        dao.salvar(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorEmail'");
    }

    @Override
    public List<Usuario> obterTodos() {
        return dao.buscarTodos();
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        //TODO: Buscar usuario existente e atualizar os dados, persistindo a alteração.
        Usuario usuarioExistente = dao.buscarPorEmail(usuario.getEmail());
        //TODO: Atualizar o usuario
        return null;
    }

    @Override
    public void excluir(Integer id) {
        dao.exluir(id);
    }
}
