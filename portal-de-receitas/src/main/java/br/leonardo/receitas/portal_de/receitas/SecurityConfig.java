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
            );

        return http.build();
    }
}
