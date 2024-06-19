# DevNotes

## Tutorial Spring Security - PARTE I

### Requisitos

Código do repositório até o dia **05/06/2024**.

### Application Properties

Iremos testar a aplicação em 2 ambientes distintos; ambiente de testes utilizando H2; ambiente de desenvolviemento utilizando banco de dados MySQL.

> Atualizem os arquivos de configuração descritos abaixo para cada um dos ambientes citados, iremos substituir a extensão .propeties por .yml.

#### Properties [resources/application.properties]

> Agora será application.yml.

#### Ativando um dos perfis.

> Após renomear o arquivo inclua o trecho abaixo.

```yaml
spring:
  profiles:
    active: test
```

#### Test Properties [resources/application-test.properties]

> Agora será application-test.yml.

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:devnotes
    username: root
    password: root
    driverClassName: org.h2.Driver
  h2:
    console:
      enabled: true
      settings:
        web-allow-others: false

  jpa:
    show-sql: true
    hibernate.ddl-auto: update
    generate-ddl: true
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.H2Dialect
```

#### Dev Properties [resources/application-dev.properties]

> Agora será application-dev.yml.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/fatec?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC
    username: root
    password: root
    driverClassName: com.mysql.cj.jdbc.Driver

  jpa:
    show-sql: true
    hibernate.ddl-auto: update
    generate-ddl: true
    properties:
      hibernate:
        format_sql: true
```

## Spring Security

### Inclua a dependencia do Spring Security no projeto.

No arquivo `build.gradle`, inclua a trecho abaixo juntamente com as demais dependencias.

```Groovy
implementation 'org.springframework.boot:spring-boot-starter-security'
```

O Spring Security será responsável pela segurança do projeto, garantindo que os recursos da API sejam disponibilizados somente aos usuários autenticados com o devido nível de acesso concedido.

### Teste inicial Spring Security

Após a criação do projeto, realize a requisição ao end-point `http://localhost:9000/api/v1/categorias` para verificar se o end-point está bloqueado, caso esteja prosseguiremos para as configurações do Spring Security.

1. Faça a requisição no Web Browser `http://localhost:8080/api/v1/test`. Um tela para autenticação deverá aparecer no navegador; caso não apareça para a execução do projeto e o abra novamente. O usuário padrão para o Spring security é `user`, a senha é gerada sempre que a aplicação for inciada.
2. Localize a senha e a copie para realizar a autenticação para a requisição realizada no Web Browser. Para a localizar, verifique o console pela string `Using generated security password`, a senha tem o pattern UUID como o exemplo abaixo.

```shell
Using generated security password: 2aff3b86-95df-4d47-a133-317081e02127
```

### Autenticação InMemory

Para compreendermos as principais configurações do Spring Security, iremos implementar a segurança urilizando validação em memória e depois por banco de dados.

#### Classes de configuração

Iremos criar um arquivo de configuração para as autenticações da API. As anotaçõesdo Spring Boot facilitam o precesso de configuração do projeto permitindo que os desenvolvedores possam ser mais produtivos e dispensem menos tempo em configurações de projeto.

1. Crie o arquivo com a classe `/api/v1/config/SecurityConfig.java`.
2. Inclua as anotações `@Configuration` e `@EnableWebSecurity`.
3. Extenda a classe `WebSecurityConfigurerAdapter `. A classe deve estar como o trecho abaixo.

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // TODO: restante do código...
}
```

4. Faça a sobrescrita do método `configure` que contenha na assinatura a classe `HttpSecurity`.

```java
        // Recursos que não precisarão de autenticação
        final String[] PUBLIC_MATCHERS = { "/h2-console/**", "/api/v1/categorias" };

        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll() // Liberar recursos listados
                .anyRequest().authenticated() // Inferir autenticação nos demais recursos
                .and()
                    .httpBasic() // Utilizar autenticação básica ao invés de página padrão do Spring
                .and()
                    .csrf() 
                    .disable();  // Desabilitar Cross Site Request Forgery             

        http.headers().frameOptions().disable(); // Necessário para sistema de frames utilizados pelo H2
```

#### Autenticação InMemory

O Spring Security possui formas distintas para facilitar a autenticação; neste exemplo iremos utilizar a autenticação com configuração de usuários e roles em memória, esta é a forma mais simples de configuração.

Resumidamente, as requisições são capturadas e são filtradas conforme as configurações da classed `SecurityConfig.java`; porém é necessário validar os usuários e suas permissões, para tal, precisamos de um `Service` que obtenha as credenciais suas respectivas permissões.

Nesta parte do tutorial iremos implementar a autenticação em memória. O service `UserDetailsService` é responsável pela obtenção das credenciais.

Realize os passos abaixo:

1. Na classe `SecurityConfig`, faça a sobrescrita do método `userDetailsService` e tranforme-o em um `Bean` para que fique globalmente disponível na aplicação.

```java
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("usuario")
                .password("123456")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
```

2. Remova a url do end-point de testes do `PUBLIC_MATCHERS`.
3. Reinicie e aplicação e teste o end-point com as credenciais `InMemory`.
4. No PostMan inclua as credenciais acima no header da request.

# PARABÉNS! Terminamos a primeira Parte I

Na próxima parte iremos implementar a autenticação em banco de dados.