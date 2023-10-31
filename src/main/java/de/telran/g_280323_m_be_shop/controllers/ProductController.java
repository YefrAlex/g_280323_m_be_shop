package de.telran.g_280323_m_be_shop.controllers;


import de.telran.g_280323_m_be_shop.domain.entity.interfaces.Product;
import de.telran.g_280323_m_be_shop.service.common.CommonProductService;
import de.telran.g_280323_m_be_shop.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService = new CommonProductService();

    // GET -> http://localhost:8080/products/all
    @GetMapping ("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }
}
