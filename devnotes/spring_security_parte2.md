
# Tutorial Spring Security - PARTE II

## Usuários e Permissões

Para o devido controle de acesso dos usuários e seus privilégios precisaremos implementar as classes que abstraem as permissões.

O Spring Boot possui abstrações para facilitar a implementação; as interfaces `UserDetails` e `GratAuthority`, ambas possuem os métodos de base para implementar as validações de acesso. Iremos implementar uma versão simplificada das interfaces nas classes Usuario e Role.

Siga os passos abaixo:

1. Crie a classe `Usuario` no pacote `domain` que implementa a interface `UderDetails`.

```java
public class Usuario implements UserDetails {

    private String nome;
    private String email;
    private String login;
    private String senha;
}    
```

2. Faça `override` dos métodos da interface. Sobre o identificador da classe pressione as teclas `CTRL + .` e escolha a opção `Override/Implement Methods`.

```java
@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
```

3. Crie a classe `Role` e implemente a interface `GrantAuthority`.

```java
public class Role implements GrantedAuthority{

    private Long id;
    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}

```

4. Transforme a classes `Usuario` e `Role` entidades gerenciada pelo JPA. Adicione a anotação `@Entity` e crie as chaves primárias com os atributos id do tipo Long.

```java
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    ...

}
```


```java
@Entity
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ...
}

```

5. Vamos deixar especificado o relacionamento na entidade role. O relacionamento será criado na entidade usuario.

```java
@Entity
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    // Relacionamento com a entidade usuario
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return nome;
    }

}
```

6. Finalize a configuração dos usuários na classe `Usuario`. Iremos ajustar os valores dos métodos da implementação de `UserDetails` e a criação do relacionamento com a classe `Role`.

Ajuste dos retornos.
```java
@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
```

Relacionamento ORM

```java
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuario_role", 
        joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private List<Role> roles;
```

## Criação de usuários em banco de dados

Iremos persisitr em banco de dados os usuários e os perfis de acesso para posterior configuração na classe `SecurityConfig.java`

### Criação de usuários na base de dados `H2`.

1. Altere o arquivo `application-test.properties` e verifique a linha abaixo; ela irá permitir a inclusão de registros no banco de dados na inicialização. As instrções em SQL devem estar no arquivo `data.sql` no diretório `resources`.

```properties
spring.jpa.defer-datasource-initialization=true
```

2. Crie o arquivo `reosources/data.sql`.
3. Inclua as instruções SQL abaixo. Elas criarão 2 usuários, um com o perfil `USER`(aluno) e outro com o perfil `ADMIN`(admin), a senha cadastrada para os 2 usuários é `123456`(elas estão cifradas pelo `BCryterPasswordEncoder`).

```sql
insert into `role`(id,nome) values (1, 'ROLE_USER');
insert into `role`(id,nome) values (2, 'ROLE_ADMIN');

insert into usuario(id, nome,email,login,senha) values (1, 'Aluno','aluno@fatec.sp.gov.br','aluno','$2a$10$6eqxIiYAiHC2Op3Ktt4FNOXrKRrW./vpq0gXNMoiwk.6cb2qvl.rq');
insert into usuario(id, nome,email,login,senha) values (2, 'Admin','admin@fatec.sp.gov.br','admin','$2a$10$6eqxIiYAiHC2Op3Ktt4FNOXrKRrW./vpq0gXNMoiwk.6cb2qvl.rq');

insert into usuario_role(id_usuario, id_role) values(1, 1);
insert into usuario_role(id_usuario, id_role) values(2, 2);
```

### Respositories e Services para validação de usuários e permissões

Com os usuários inseridos na base de dados precisamos remover a autenticação em memória e realizá-la via banco de dados, para tal, precisaremos recuperá-las com um repository e acriar um service para que o Spring possa urtilizá-lo sempre que requisições forem capturadas pela API.

1. Crie a interface `repositories/UsuarioRepository.java` e descreva o método `findUsuarioByLogin`, o Spring se encarrega do resto.

```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findUsuarioByLogin(String login);
}

```

2. Remova o `@Bean` de autenticação em memória da classe `SecurityConfig`; não precisaremos mais dele.
3. No pacote `security` crie a classe `AutenticacaoService.java`, ela será o serviço que fornecerá os usuários e suas pemissões ao Spring; ela deve implementar a interface `UserDetailsService` com o método `loadUserByUsername`, por sua vez o método chama o repository relativo aos usuários e seu perfis; desta forma o Spring pode validá-los sempre que uma requisição for capturada.

```java
package br.com.fatecararas.pdevhowto.api.v1.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fatecararas.pdevhowto.domain.Usuario;
import br.com.fatecararas.pdevhowto.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findUsuarioByLogin(username);

        if(Objects.isNull(usuario)) {
            throw new RuntimeException("Usuário não localizado.");
        }

        return usuario;
    }
    
}
```
5. Crie um Bean para cifragem de senhas no diretório `security/config/AppConfig.java`.

```java
@Configuration
public class AppConfig {
    
    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

6. Altere a classe `SecurityConfig` para utilização do `AutenticacaoService` no lugar a autenticação em memória.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Injeção de dependencia do BCrypter para cifrar senhas
    @Autowired BCryptPasswordEncoder encoder;

    // Inclusão do serviço de validação de usuários
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        final String[] PUBLIC_MATCHERS = { "/h2-console/**"};

        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll() 
                .anyRequest().authenticated()
                .and()
                    .httpBasic()
                .and()
                    .csrf()
                    .disable(); 

        http.headers().frameOptions().disable(); 
    }

    // Sobrecarga do método configue com assinatura AuthenticationManagerBuilder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
       auth
        .userDetailsService(autenticacaoService) // Configuração de serviço que criamos so Spring
        .passwordEncoder(encoder); // Definição do codificador de senhas
    }
}

```

### Testes de autenticação

Neste ponto temos a segurança básica configurada; os usuários serão validados conforme os registros na base de dados.

Realize os testes de autenticação com os usuários `aluno` e `admin` no end-point de testes `http://localhost:9000/api/v1/categorias/todas`.

Caso utilize o navegador, lembre-se de limpar o Cookies referentes à autenticação dos 2 usuários, preferencialmente utilize o `PostMan`, `Insomnia`  ou o plugin `ThundeClient` para o `VSCode`.

> Uma das próximas tarefas será a realização destes testes para métodos e plugins específicos, aproveite agora para tirar dúvidas e ganhar expertise com o Spring Security.

### Teste do Perfil ADMIN

Vamos alterar o end-point de testes para acesso somente aos usuários `ADMIN`, os usuários comuns não terão permissão de acesso ao recurso.

1. Na classe `SecurityConfig` inclua o matcher `ADMIN_MATCHERS` abaixo do `PUBLIC_MATCHERS`.

```java
final String[] ADMIN_MATCHERS = { "/api/v1/test/**"};
```

2. Inclua o buildMethod `.antMatchers(ADMIN_MATCHERS).hasRole("ADMIN") ` a configuração do objeto `http` do método configure; esta instrução irá liberar os end-points do `ADMIN_MATCHERS` apenas aos perfis de administradores.

```java
        .authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS).permitAll()
        .antMatchers(ADMIN_MATCHERS).hasRole("ADMIN") 
```

> Os filtros podem ser feitos por vebos também, como neste exemplo .antMatchers(HttpMethod.POST, ADMIN_MATCHERS).hasRole("ADMIN"), sendo necessário apenas a substituição dos verbos e as roles.

#### Realize os testes ambos os usuários

### Exceções Customizadas

As exceções em APIs, se não forem gerenciadas adequadamente podem gerar informações quase que ilígeveis ao usuários. O Spring oferece recursos para capturar exceções e apresentá-las de forma mais amigável no retorno das requisições.

Iremos implementar o `ControllerAdvice`, com ele, podemos capturar exceções específicas e criar respostas mais efetivas que o tradicional Status Code 500. Siga os passos abaixo.

1. Crie o método `acessoNegado`na classe `ApplicationExceptionHandler`. Quando uma `AccessDeniedException` for lançada, a saída padrão será substituida pelo Json da classe `ApiError`.

```java
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> accesDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiError("Acesso Negado"));
    }
```

#### Realize os testes ambos os usuários e valide a exception para o aceesso negado.

# PARABÉNS! Terminamos a segunda parte!

### Testes da API

Realize os testes básico do CRUD com as dicas.

Crie perfis diversos e modifique o `SecurityConfig`.

# PARABÉNS POR CHEGAR ATE AQUI