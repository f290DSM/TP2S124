package model.reposirory;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioMemoriaRepositoryImpl implements UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public Usuario criar(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> obterTodos() {
        return usuarios;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        Usuario usuarioAtualizado = null;
        for (int i = 0; i < usuarios.size(); i++) {
            usuarioAtualizado = usuarios.get(i);
            if (usuario.getId().equals(usuarioAtualizado.getId())) {
                usuarioAtualizado.setNome(usuario.getNome());
                usuarioAtualizado.setEmail(usuario.getEmail());
                usuarioAtualizado.setSenha(usuario.getSenha());
                break;
            }
        }
        return usuarioAtualizado;
    }

    @Override
    public void excluir(Integer id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.remove(i);
                break;
            }
        }
    }

}
