package br.leonardo.receitas.portal_de.receitas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilitar CSRF se não estiver usando formulários
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**") // Permitir acesso a estas URLs
                .permitAll()
                .anyRequest().authenticated() // Requerer autenticação para todas as outras URLs
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            )
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; script-src 'self' https://trustedscripts.example.com; form-action 'self' https://turbo-space-palm-tree-jj56jx74vx5xfp579-8081.app.github.dev; style-src 'self' 'unsafe-inline';")
                )
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitir que o conteúdo seja carregado em iframes da mesma origem
            );

        return http.build();
    }
}
