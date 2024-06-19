package br.edu.fatecararas.devnotes.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        final String[] PUBLIC_MATCHERS = { "/h2-console/**" };
        http
                .authorizeRequests()
                .requestMatchers(PUBLIC_MATCHERS).permitAll() // Liberar recursos listados
                .anyRequest().authenticated() // Inferir autenticação nos demais recursos
                .and()
                .httpBasic() // Utilizar autenticação básica ao invés de página padrão do Spring
                .and()
                .csrf()
                .disable();  // Desabilitar Cross Site Request Forgery

        http.headers().frameOptions().disable(); // Necessário para sistema de frames utilizados pelo H2

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("usuario")
                .password("123edc")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
