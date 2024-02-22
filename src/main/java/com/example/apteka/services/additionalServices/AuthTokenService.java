package com.example.apteka.services.additionalServices;

import com.example.apteka.models.additionalModels.AuthToken;
import com.example.apteka.repositories.additionalRepositories.AuthTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public void saveAuthToken(AuthToken authToken) {

        authTokenRepository.save(authToken);
    }

    public AuthToken findByUserId(Long userId) {
        return authTokenRepository.findByUserId(userId);
    }

}
