package com.ToDomangemnet.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(authorize-> authorize
//                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole("ADMIN")
                       .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(passwordEncoder().encode("password2"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
