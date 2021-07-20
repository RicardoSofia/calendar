package com.api.calendar.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
            .inMemoryAuthentication()
            .withUser("ines")
            .password(encoder.encode("ines"))
            .roles("INTERVIEWER")
            .and()
            .withUser("ingrid")
            .password(encoder.encode("ingrid"))
            .roles("INTERVIEWER")
            .and()
            .withUser("candidate")
            .password(encoder.encode("candidate"))
            .roles("CANDIDATE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/interviewCalendar/**").permitAll()
//            .antMatchers("/scheduleCalendar/**").hasRole("INTERVIEWER")
            .antMatchers("/scheduleCalendar/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin()
            .loginPage("/login").permitAll();
    }
}