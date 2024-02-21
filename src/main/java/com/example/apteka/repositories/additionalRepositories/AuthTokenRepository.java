package com.example.apteka.repositories.additionalRepositories;

import com.example.apteka.models.additionalModels.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {
    AuthToken findByUserId(Long userId);
    AuthToken save(AuthToken authToken);
}
