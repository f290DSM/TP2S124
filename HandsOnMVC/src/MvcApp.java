import java.sql.Connection;
import model.dao.ConexaoFactory;
import model.dao.IUsuarioDAO;
import model.dao.UsuarioDAO;

public class MvcApp {
    public static void main(String[] args) throws Exception {

        Connection conexao = ConexaoFactory.getConexao();
        IUsuarioDAO dao = new UsuarioDAO(conexao);
    }
}
