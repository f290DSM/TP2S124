# HandsOnMVC

Neste projeto iremo realizar a implemnatação do Design Pattern `MVC`, o Model View Controller.

Porém iremo focar inicialmente na camada **model** já aplicando os conhecimentos de `S.O.L.I.D` trabalhados anteriormente.

Nosso objetivo é criar uma simples aplicação de controle de usuários, porém utilizando boas práticas e técnicas de programação visando a alta coesão, baixo acoplamento e mais fácil de testar.

## A Camada MODEL

A camada `MODEL` contém resumidamente, regras de negócio e os `VOs`, `DTOs`, domain classes, `SERVICES`, `REPOSITORIES`, `DAO` e demais classes que vehnam a contribuir para com o desenvolvimento das regras de negócios de seu projeto.

> Neste ponto você já deve ter um projeto Java criado, então vamos seguir com a criação detalhada de cada classe nesta camada.

### O pacote model

Este pacote irá conter as abstrações e implementações das regras de negócio e representará a camda `MODEL` do `MVC`.

1. Crie o diretório/pacote `model` dentro da pasta `src`;
2. Crie a classe `src/model/Usuario.java` e inclua o código abaixo.

```java
package model;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }    
}
```
3. Crie o pacote `service` em `src`. 
4. Crie a classe de serviços `src/services/UsuarioService.java`.
5. Crie o pacote `repositories` em `src`.
6. Crie o contrato `UsuarioRepository.java`, este aquivo será uma interface. Inclua as `cláusulas` do contrato.

```java
package model.reposirory;

import java.util.List;

import model.Usuario;

public interface UsuarioRepository {
    void criar(Usuario usuario) ;

    Usuario buscarPorEmail(String email);

    List<Usuario> obterTodos();

    Usuario atualizar(Usuario usuario);

    void excluir(Integer id);
}
```

> Este contrato será utilizado para que possamos utilizar repositórios de dados diversificados; ou seja, podemos utilizar tanto um repositório em memória, quanto repositórios com banco de dados.



