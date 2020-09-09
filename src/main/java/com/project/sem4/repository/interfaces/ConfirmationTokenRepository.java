package com.project.sem4.repository.interfaces;

import com.project.sem4.model.service.ConfirmationToken;

public interface ConfirmationTokenRepository {
    public Boolean addConfigToken(ConfirmationToken addConfigToken);
    public Boolean deleteToken(Long id);
    public ConfirmationToken findTokenByToken(String token);
}
