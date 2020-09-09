package com.project.sem4.service;

import com.project.sem4.model.Users;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepositoryImpl userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = this.userRepository.loadUserByUsername(email);
        if(users.getUserID() != null && users.getEnabled()){
            List<String> roleNames = this.userRepository.getRoleByUser(users.getUserID());
            List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
            if (roleNames != null) {
                for (String role : roleNames) {
                    GrantedAuthority authority = new SimpleGrantedAuthority(role);
                    grantList.add(authority);
                }
            }
            UserDetails userDetails = (UserDetails) new User(users.getEmail(), //
                    users.getPassword(), grantList);
            return userDetails;
        }else {
            System.out.println("không đăng nhập đc rồi ahihi");
            throw new UsernameNotFoundException("username not found");
        }
    }
}
