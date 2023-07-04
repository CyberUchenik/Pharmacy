package com.example.apteka.repositories;

import com.example.apteka.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);
}
