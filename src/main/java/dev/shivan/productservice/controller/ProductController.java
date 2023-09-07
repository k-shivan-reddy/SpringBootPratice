package dev.shivan.productservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public void getAllProducts() {

    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Here is product id: " + id;
    }

    @DeleteMapping("{id}")
    public void deleteProductById() {

    }

    @PostMapping
    public String createProduct() {
        return "Created new product with id : " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById() {
    }
}
