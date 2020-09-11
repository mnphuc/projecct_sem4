package com.project.sem4.repository.interfaces;

import com.project.sem4.model.Role;
import com.project.sem4.model.Users;
import com.project.sem4.model.service.ConfirmationToken;
import com.project.sem4.model.view.InsertUser;
import org.apache.catalina.User;

import java.util.List;

public interface UserRepository {
    public Users loadUserByUsername(String email);
    public List<String>  getRoleByUser(Long userId);
    public List<String> getAllRole();
    public Boolean insertUser(InsertUser insertUser);
    public Boolean insertUserClient(InsertUser insertUser);
    public Boolean insertRoleUser(Long idUser, Integer idRole);
    public Users getUserByEmail(String email);
    public List<Users> getAllUser();
    public List<Role> getAllRoles();
    public Boolean confirmUsers(Long userId, Boolean enabled);
    public Users findUserById(Long id);
    public Boolean findUserByEmail(String email);
}
