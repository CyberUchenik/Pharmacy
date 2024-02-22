package com.example.apteka.controllers;

import com.example.apteka.models.Product;
import com.example.apteka.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/admin/products")
    public String products(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("products",productService.listProducts(name));
        return "products";
    }
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id,Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product",productService.getProductById(id));
        model.addAttribute("images",product.getImages());
        model.addAttribute("totalPrice", 0);
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,Product product) throws IOException {
        productService.saveProduct(product,file1,file2,file3);
        return "redirect:/admin/products";//TODO Его давно нету
    }
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @RequestParam("name") String name,
                                @RequestParam("description")String description,@RequestParam("price") int price,
                                @RequestParam("quantity") int quantity,@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3)
                                throws IOException {
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        productService.saveProduct(product,file1,file2,file3);
        return "redirect:/product/" + id;
    }
    @PostMapping("/product/buy/{id}")
    public String sellProduct(@PathVariable("id") long id,@RequestParam("quantity") int quantity,Model model){
        Product product = productService.getProductById(id);

        //Получаем цену товара по айди
        int price = product.getPrice();

        // Обновляем количество товара и общую стоимость в модели
        model.addAttribute("product", product);

        //Вызываем метод из Сервиса productService
        productService.buyProduct(id,quantity,price);

        return "redirect:/product/" + id;
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
