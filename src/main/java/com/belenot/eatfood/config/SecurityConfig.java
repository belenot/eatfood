package com.belenot.eatfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("default")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/client/signup", "/client/signin").anonymous()
                .antMatchers("/eatfood", "/favicon.ico", "/static/**/*", "/js/**/*").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/eatfood").successForwardUrl("/eatfood").and()
                .logout().logoutSuccessUrl("/eatfood").and()
                .csrf().disable();
        // .anyRequest().authenticated()
        // .antMatchers("/login", "/signup").anonymous();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    
    
}