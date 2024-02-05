package com.example.apteka.services;

import com.example.apteka.models.User;
import com.example.apteka.models.additionalModels.Role;
import com.example.apteka.repositories.additionalRepositories.RoleRepository;
import com.example.apteka.repositories.additionalRepositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден",username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getFirstname(),
                user.getHashed_password(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList())
        );
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByFirstname(username);
    }

    public void createNewUser(User user){
        user.setRoles(List.of(roleRepository.findByRoleName("ROlE_USER").get()));
        userRepository.save(user);
    }

}
