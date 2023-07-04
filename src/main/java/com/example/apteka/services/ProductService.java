package com.example.apteka.services;

import com.example.apteka.models.Image;
import com.example.apteka.models.Product;
import com.example.apteka.models.Sale;
import com.example.apteka.repositories.ProductRepository;
import com.example.apteka.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private final SaleRepository saleRepository;
    public List<Product> listProducts(String name) {
        if (name != null) return productRepository.findByName(name);
        return productRepository.findAll();
    }


    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setIsPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file1);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file1);
            product.addImageToProduct(image3);
        }

        log.info("Saving new Product. name: {}; price: {}; ", product.getName(), product.getPrice());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void sellProduct(Long productId, int quantity, int price){
        Product product = getProductById(productId);
        // Проверить наличие достаточного количества товара на складе
        if(product != null){
            if(product.getQuantity() >= quantity){

                // Вычесть проданное количество из имеющегося на складе
                product.setQuantity(product.getQuantity() - quantity);


                // Сохранить изменения в товаре
                productRepository.save(product);

                Sale sale = new Sale();
                sale.setName(product.getName());
                sale.setQuantity(quantity);
                sale.setPrice(price);
                sale.setSaleDate(LocalDateTime.now());
                // Сохранить запись о продаже
                saleRepository.save(sale);
            }else {
                throw new IllegalStateException("Недостаточное количество товара на складе");
            }
        }else {
            throw new IllegalStateException("Нет в наличий");
        }
    }
    // Метод для подсчета общей суммы продаж на заданный период
    public int getTotalSalesByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = saleRepository.findBySaleDateBetween(startDate, endDate);

        int totalSales = 0;
        for (Sale sale : sales) {
            totalSales += sale.getQuantity() * sale.getPrice();
        }
        return totalSales;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
