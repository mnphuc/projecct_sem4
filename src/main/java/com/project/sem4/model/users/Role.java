package com.project.sem4.model.users;

import org.springframework.expression.Operation;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class Role implements GrantedAuthority {
    private String id;
    private final List<Operation> allowedOperations = new ArrayList<>();
    @Override
    public String getAuthority() {
        return id;
    }

    public List<Operation> getAllowedOperations() {
        return allowedOperations;
    }
}
