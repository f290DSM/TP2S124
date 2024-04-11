import java.sql.ResultSet;
import java.util.Scanner;

import model.Usuario;
import model.dao.ConexaoFactory;
import model.reposirory.UsuarioMemoriaRepositoryImpl;
import model.reposirory.UsuarioRepository;
import services.UsuarioService;

public class MvcApp {
    public static void main(String[] args) throws Exception {
        // TODO: Inicializar dependencias
        UsuarioRepository repository = new UsuarioMemoriaRepositoryImpl();
        UsuarioService service = new UsuarioService(repository);

        // TODO: Testar Service

        Scanner in = new Scanner(System.in);
        // System.out.println("Digite o nome: ");
        // String nome = in.next();
        // System.out.println("Digite o e-mail: ");
        // String email = in.next();
        // System.out.println("Digite o senha: ");
        // String senha = in.next();

        System.out.println("Qtd. Usuarios: " + service.obterTodos().size());
        var u1 = new Usuario(1, "Jose", "jose@gmail.com", "123edc");
        var u2 = new Usuario(2, "Pedro", "pedro@gmail.com", "123edc");
        var u3 = new Usuario(3, "Antonio", "antonhio@gmail.com", "123edc");

        service.criar(u1);
        service.criar(u2);
        service.criar(u3);

        System.out.println("Qtd. Usuarios: " + service.obterTodos().size());

        System.out.println();
        service.obterTodos().forEach(c -> System.out.println(c));

        System.out.println();
        service.excluir(u2.getId());
        u1.setNome("Maria");
        u1.setEmail("maria@gmail.com");

        System.out.println();
        service.obterTodos().forEach(c -> System.out.println(c));
        System.out.println("Qtd. Usuarios: " + service.obterTodos().size());
        in.close();

        ResultSet rst = ConexaoFactory.getConexao()
                .createStatement()
                .executeQuery("select version() as versao");

        if (rst.next()) {
            String versao = rst.getString("versao");
            System.out.println(versao);   
        }


                

                     
    }
}
