package com.project.sem4.config;

import com.project.sem4.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {

        // Cấu hình security/form login cho url /admin/**
        // Cấu hình cho Login Form.
        http.antMatcher("/quan-tri/**").authorizeRequests().antMatchers("/quan-tri/**").access("hasRole('ROLE_ADMIN')").and().formLogin()//
                .loginProcessingUrl("/quan-tri/login-submit")//
                .loginPage("/dang-nhap-quan-tri")//
                .defaultSuccessUrl("/quan-tri/don-hang/")//
                .failureUrl("/dang-nhap-quan-tri?message=error")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/quan-tri/logout", "GET")).logoutSuccessUrl("/dang-nhap-quan-tri?message=logout");
    }

}
