package com.example.apteka.utils;

import com.example.apteka.models.additionalModels.AuthToken;
import com.example.apteka.services.AuthService;
import com.example.apteka.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO Нужно будет создать сущность который будет ловить все данные хранить в базе данных токены которые указаны на схеме
@Component
@Slf4j
public class JwtTokenUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf")
    private String secret;

    @Value(value = "30m")
    private Duration jwtLifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roleList);
        Date issueDate = new Date();
        Date expired_at = new Date(issueDate.getTime() + jwtLifetime.toMillis());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issueDate)
                .setExpiration(expired_at)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        logger.info("Generated Token: {}", token);

        return token;
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
//    public Date getIssueDate(String token) {
//        return getAllClaimsFromToken(token).getIssuedAt();
//    }
//    public Date getExpirationDate(String token) {
//        return getAllClaimsFromToken(token).getExpiration();
//    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    //TODO взять токен
}
