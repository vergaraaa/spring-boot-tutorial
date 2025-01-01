package com.example.nobsv2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User
            .withUsername("admin")
            .authorities("BASIC", "SPECIAL")
            .roles("superuser")
            .password(encoder.encode("1")) // spring security requires password to be encoded
            .build();
            
            UserDetails user = User
            .withUsername("user")
            .authorities("BASIC")
            .roles("basicuser")
            .password(encoder.encode("2")) // spring security requires password to be encoded
            .build();


        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // allows for POST, PUT, DELETE mappings with authentication
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> {
                authorize.anyRequest().authenticated();
                // authorize.requestMatchers("/open").permitAll();
                // authorize.requestMatchers("/closed").authenticated();
                // authorize.requestMatchers(HttpMethod.POST, "/product").authenticated();

                // authorize.requestMatchers(HttpMethod.GET, "/special").hasAuthority("SPECIAL");
                // authorize.requestMatchers(HttpMethod.GET, "/basic").hasAnyAuthority("BASIC", "SPECIAL");
            })
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
