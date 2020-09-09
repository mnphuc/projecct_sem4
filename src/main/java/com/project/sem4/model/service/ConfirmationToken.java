package com.project.sem4.model.service;

import com.project.sem4.model.Users;
import com.project.sem4.model.users.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {
    private Long id;
    private String confirmationToken;
    private LocalDate createdDate;
    private Long userId;
    private Users user;
    public ConfirmationToken(Users user) {
        this.user = user;
        this.createdDate = LocalDate.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }
}
