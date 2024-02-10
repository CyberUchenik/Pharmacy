package com.example.apteka.services;

import com.example.apteka.models.User;
import com.example.apteka.models.additionalModels.Role;
import com.example.apteka.repositories.additionalRepositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){return roleRepository.findByRoleName("ROLE_USER").get();}

}
