package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    //TODO: Mudar para a senha que seja compativel com sua base de dados
    private static final String PASSWORD = "segredo";
    
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
