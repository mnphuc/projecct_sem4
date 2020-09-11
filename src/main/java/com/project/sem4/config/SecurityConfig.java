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
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
   // UserDetailsServiceImpl userDetailsService;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cấu hình cho Login Form.
        http.authorizeRequests().antMatchers("/thanh-toan/**", "/lich-su-mua-hang/**", "/chi-tiet-don-hang/**").access("hasRole('ROLE_USER')").and().formLogin()
                .loginProcessingUrl("/dang-nhap-submit")//
                .loginPage("/dang-nhap")//
                .defaultSuccessUrl("/")//
                .failureUrl("/dang-nhap?message=error")//
                .usernameParameter("email")
                .passwordParameter("pass")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/dang-nhap?message=logout");
    }
}