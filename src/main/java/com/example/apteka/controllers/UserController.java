package com.example.apteka.controllers;

import com.example.apteka.models.Product;
import com.example.apteka.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final ProductService productService;

    @GetMapping("/")
    public String userProducts(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("products", productService.listProducts(name));
        return "user-products";//TODO создать файл для юзера продуктс
    }

    @GetMapping("/user/product/{id}")
    public String userProductInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("images", product.getImages());
        return "user-product-info"; //TODO создать файл для юзера инфо
    }
    @PostMapping("/user/product/buy/{id}")
    public String buyProduct(@PathVariable("id") long id, @RequestParam("quantity") int quantity, Model model) {
        Product product = productService.getProductById(id);

        // Получаем цену товара по айди
        int price = product.getPrice();

        // Обновляем количество товара и общую стоимость в модели
        model.addAttribute("product", product);

        // Вызываем метод из Сервиса productService для выполнения операции покупки
        productService.buyProduct(id, quantity, price);

        return "redirect:/user/product/" + id;//TODO создать файл для юзера
    }




}
