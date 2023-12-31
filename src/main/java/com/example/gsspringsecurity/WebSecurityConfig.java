package com.example.gsspringsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers( "/","/home").permitAll()
                            //.antMatchers("/","/home").permitAll()
                            //.requestMatchers(new AntPathRequestMatcher("/","/home")).permitAll()
                            .anyRequest().authenticated()
            )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .permitAll()
            )
                    .logout(logout -> logout
                            .permitAll());
                    http.formLogin();
            return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                                .withDefaultPasswordEncoder()
                                .username("user")
                                .password("password")
                                .roles("USER")
                                .build();
            return new InMemoryUserDetailsManager(user);
    }
}
