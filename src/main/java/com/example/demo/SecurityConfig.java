package com.example.demo;

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
                .authorizeHttpRequests((requests) -> requests
                        // Permite acesso livre a pastas de estilo e imagem (se tivermos no futuro)
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        // Exige login para qualquer outra página
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        // Avisa que a nossa página de login é a /login
                        .loginPage("/login")
                        // Se der certo, vai para a /lista
                        .defaultSuccessUrl("/lista", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())

                // --- AQUI ESTÁ A CORREÇÃO (Nova sintaxe do Spring) ---
                // Habilita o acesso ao banco H2 no navegador desativando bloqueios de segurança
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}