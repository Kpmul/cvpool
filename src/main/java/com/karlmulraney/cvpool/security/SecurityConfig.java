package com.karlmulraney.cvpool.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails karl = User.builder()
                .username("karl")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();
        
        return new InMemoryUserDetailsManager(karl);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                configurer
                    .requestMatchers(HttpMethod.GET, "/","/home").permitAll()
                    .requestMatchers(HttpMethod.GET,"/css/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/document/all").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/document/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/document/upload").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/document/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/document/**").hasRole("ADMIN")
                    
        );

        // Use basic auth
        //http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());


        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
