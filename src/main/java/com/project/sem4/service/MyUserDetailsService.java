package com.project.sem4.service;

import com.project.sem4.model.Users;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepositoryImpl loadUserByUsername;
    @Override
    public UserDetails loadUserByUsername(final String username ) throws UsernameNotFoundException {
        return null;
//        Users user = loadUserByUsername.loadUserByUsername(username);
//        if (user == null){
//            throw new UsernameNotFoundException("Tài Khoản Không Tồn Tại");
//        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//
//        return new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
//                accountNonLocked, user.getAuthorities());
    }
}
