package kth.alex.demo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthConverter jwtAuthConverter;

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/doctor/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(t -> {
            t.disable();
        });
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(AUTH_WHITE_LIST)
                    .permitAll()
                    .requestMatchers(HttpMethod.POST,"/patient")
                    .permitAll()
                    .anyRequest().authenticated();
        });
        http.oauth2ResourceServer(t -> {
            t.jwt(jwtConfigurer -> {
                jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter);
            });
        });
        http.sessionManagement(t -> {
            t.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        return http.build();
    }
}
