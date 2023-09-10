package dev.shivan.productservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

    //field injection
    //we can do injection by using @AutoWired, This is not recommended new people cannot help here
    private ProductService productservice;

    //Constructor injection 
    public ProductController(@Qualifier("fakestoreserviceImpl") ProductService productservice)
    {
        this.productservice=productservice;
    }
    //setter injection 
    //@AutoWired
    // public void setProductService()
    // {
    //     this.productservice=productservice;
    // }

    @GetMapping
    public void getAllProducts() {

    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return productservice.getProductById(id);
    }


    @DeleteMapping("{id}")
    public void deleteProductById() {

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        //return "Created new product with id : " + UUID.randomUUID();
        //return "created ned product with name : "+ productDto.getTitle();
        return productservice.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById() {
    }
}
