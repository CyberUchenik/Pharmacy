package com.example.apteka.services;

import com.example.apteka.models.Product;
import com.example.apteka.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> listProducts(String name) {
        if (name != null)return productRepository.findByName(name);
        return productRepository.findAll();
    }
    public void saveProduct(Product product){
        log.info("Saving new{}",product);
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
