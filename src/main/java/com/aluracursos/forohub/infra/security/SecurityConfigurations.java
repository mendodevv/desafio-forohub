package com.aluracursos.forohub.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Forma actualizada de deshabilitar CSRF
                .authorizeRequests()
                .requestMatchers("/**").permitAll() // Permite todas las solicitudes
                .anyRequest().authenticated()
                .and()
                .httpBasic(httpBasic -> httpBasic.disable()); // Forma actualizada de deshabilitar la autenticación básica
        return http.build();
    }
}