package com.project.sem4.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
@Data
public class Users {
    private Long userID;
    private String fullName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    private Boolean locked;
    private Boolean enabled;

}
