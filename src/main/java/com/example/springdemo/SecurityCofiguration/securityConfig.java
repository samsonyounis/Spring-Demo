package com.example.springdemo.SecurityCofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class securityConfig{
    @Autowired
    private userDetailsService myUserDetailsService; // instance of userDetailsService class
    @Autowired
    private jwtRequestFilter requestFilter;
    @Autowired
    private  AuthenticationEntryPoint authenticationEntryPoint;

    //Authentication Provider
    // instead of overriding this (//public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)in the WebSecurityConfigurerAdapter

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
//instead of overriding the AuthenticationManagerBean in the WebSecurityConfigurerAdapter.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }



    //bean to return the BcryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                //this shows that the session is stateless.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //our public endpoints.
                .authorizeHttpRequests().requestMatchers(
                        "/api/login","/api/addUser","/api/addRole","/api/getUserRoles/**",
                        "/api/getAll",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/v2/api-docs",
                        "/v3/api-docs.yaml")
                .permitAll().requestMatchers("/api/getlast").
                //"/employee/add"
                hasRole("admin")
                // these are the private end points.
                .anyRequest().authenticated();
        //this adds the authentication provide bean above
        http.authenticationProvider(authenticationProvider());
        //this adds the jwtTokenFilter for validation before accessing any api.
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
