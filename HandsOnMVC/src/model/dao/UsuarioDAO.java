package model.dao;

import java.util.*;

import java.sql.*;

import model.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    // TODO: Incluir dependencia de conexao
    private final Connection conexao;

    // TODO: Fazer inversão/injeção de dependencia
    public UsuarioDAO(Connection connection) {
        this.conexao = connection;
        init();
    }

    private void init() {
        String createDatabase = "CREATE DATABASE IF NOT EXISTS fatec;\n";
        String createTable = "CREATE TABLE IF NOT EXISTS fatec.usuarios("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "nome VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) NOT NULL, "
                + "senha VARCHAR(100) NOT NULL);";

        try (Statement stm = conexao.createStatement()) {
            stm.execute(createDatabase);
            stm.execute(createTable);
        } catch (Exception e) {
            System.out.println("Erro ao criar a entidade usuarios. Erro: "
                    + e.getLocalizedMessage());
        }
    }


    @Override
    public void salvar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO fatec.usuarios (nome, email, senha) "
                + "values ('%s', '%s', '%s')";

        try (Statement stm = conexao.createStatement()) {
            stm.execute(sql);
        } catch (Exception e) {
            System.out.println("Erro ao criar usuario. Erro: "
                    + e.getLocalizedMessage());
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorEmail'");
    }

    @Override
    public void atualizar(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void exluir(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exluir'");
    }

    @Override
    public List<Usuario> buscarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodos'");
    }

}
