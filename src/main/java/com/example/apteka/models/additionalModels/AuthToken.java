package com.example.apteka.models.additionalModels;

import com.example.apteka.models.User;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "auth_tokens")
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //TODO нужно прописать связь между User
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "token")
    private String token;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "created_at")
    private Date createdAt;

}
