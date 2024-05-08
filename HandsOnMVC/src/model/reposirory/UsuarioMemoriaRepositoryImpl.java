package model.reposirory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Usuario;

public class UsuarioMemoriaRepositoryImpl implements UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public void criar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarios
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Usuario> obterTodos() {
        return usuarios;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return usuarios
                .stream()
                .filter(u -> u.getEmail().equals(usuario.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void excluir(Integer id) throws Exception {
        Optional<Usuario> usuario = usuarios
                .stream().filter(u -> u.getId().equals(id))
                .findFirst();

        if(usuario.isEmpty()) {
            throw new Exception("Usuário não ");
        }

        usuario.ifPresent(u -> usuarios.remove(u));
    }
}
