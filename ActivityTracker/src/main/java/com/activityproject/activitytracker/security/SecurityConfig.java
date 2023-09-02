package com.activityproject.activitytracker.security;


import com.activityproject.activitytracker.filters.CustomAuthenticationFilter;
import com.activityproject.activitytracker.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This is the main configuration class for security in the application. It enables web security,
 * sets up the password encoder, and sets up the security filter chain.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    // Instance of the AuthenticationManagerBuilder
    private final AuthenticationManagerBuilder authManagerBuilder;

    /**
     * Bean definition for PasswordEncoder
     *
     * @return an instance of the DelegatingPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Bean definition for AuthenticationManager
     *
     * @param authenticationConfiguration the instance of AuthenticationConfiguration
     * @return an instance of the AuthenticationManager
     * @throws Exception if there is an issue getting the instance of the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean definition for SecurityFilterChain
     *
     * @param http the instance of HttpSecurity
     * @return an instance of the SecurityFilterChain
     * @throws Exception if there is an issue building the SecurityFilterChain
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthenticationFilter instance created
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        // set the URL that the filter should process
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        // disable CSRF protection
        http.csrf(AbstractHttpConfigurer::disable);
        // set the session creation policy to stateless
        http.sessionManagement((session) -> session.sessionCreationPolicy(STATELESS));
        // set up authorization for different request matchers and user roles
        // modify this to have different configurations
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/v1/login").permitAll()
                .requestMatchers(GET, "/api/v1/users/me").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(GET, "/api/v1/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/v1/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(GET, "/api/v1/greet").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated());
        // add the custom authentication filter to the http security object
        http.addFilter(customAuthenticationFilter);
        // Add the custom authorization filter before the standard authentication filter.
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Build the security filter chain to be returned.
        return http.build();
    }
}