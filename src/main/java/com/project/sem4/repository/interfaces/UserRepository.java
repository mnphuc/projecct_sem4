package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UserRepository {
    public Users loadUserByUsername(String email);
    public List<String>  getRoleByUser(Long userId);
}
