package com.example.apteka.services;

import com.example.apteka.dtos.JwtRequest;
import com.example.apteka.dtos.JwtResponse;
import com.example.apteka.dtos.RegistrationUserDto;
import com.example.apteka.dtos.UserDto;
import com.example.apteka.exceptions.AppError;
import com.example.apteka.models.User;
import com.example.apteka.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService  {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        log.info("Creating token for user: {}", authRequest.getFirstname());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getFirstname(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error("Ошибка аутентификации: Неправильный логин или пароль", e);
            log.info("Неправильный логин или пароль");
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"),
                    HttpStatus.UNAUTHORIZED
            );
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getFirstname());
        String token = jwtTokenUtils.generateToken(userDetails);
        log.info("Created token for " + userDetails+ " " + token );
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getHashed_password().equals(registrationUserDto.getConfirmHashed_password())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"Пароли не совпадает"),HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getFirstname()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"),HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getFirstname(),user.getEmail()));
    }



}
