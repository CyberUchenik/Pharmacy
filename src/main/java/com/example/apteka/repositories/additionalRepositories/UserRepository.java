package com.example.apteka.repositories.additionalRepositories;

import com.example.apteka.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByFirstname(String username);

}
