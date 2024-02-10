package com.example.apteka.services;

import com.example.apteka.dtos.RegistrationUserDto;
import com.example.apteka.models.User;
import com.example.apteka.repositories.additionalRepositories.RoleRepository;
import com.example.apteka.repositories.additionalRepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден",username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getFirstname(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList())
        );
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByFirstname(username);
    }

    public User createNewUser(RegistrationUserDto registrationUserDto){
        User user = new User();
        user.setFirstname(registrationUserDto.getFirstname());
        user.setLastname(registrationUserDto.getLastname());
        user.setFathername(registrationUserDto.getFathername());
        user.setLogin(registrationUserDto.getLogin());
        user.setEmail(registrationUserDto.getEmail());
        user.setPhone(registrationUserDto.getPhone());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getHashed_password()));
        user.setDef(registrationUserDto.getDef());

        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

}
