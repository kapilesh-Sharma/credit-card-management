package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring security Basic Authentication configuration
 */

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Added HTTP methods and resource URLs which needs to be to be authenticated
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().authorizeRequests().
                antMatchers(HttpMethod.GET, "/api/v1/credit-cards/**").hasRole("USER").
                antMatchers(HttpMethod.POST, "/api/v1/credit-cards/**").hasRole("USER").
                antMatchers(HttpMethod.PUT, "/api/v1/credit-cards/**").hasRole("USER").
                and().
                csrf().
                disable();
    }

    /**
     * In memory User credentials preparation which should be ideally externalize with application.yml
     * But leaving it here as this is not the production ready model
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
                auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("password"))
                .roles("USER");
    }

}