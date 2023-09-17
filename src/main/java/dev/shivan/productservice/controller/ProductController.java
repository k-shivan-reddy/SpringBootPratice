package dev.shivan.productservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.shivan.productservice.dtos.ExceptionDto;
import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.exceptions.NotFoundException;
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
    public List<GenericProductDto>  getAllProducts() {
        return productservice.getAllProducts();

    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productservice.getProductById(id);
    }


    @DeleteMapping("{id}")
    public  ResponseEntity<GenericProductDto>  deleteProductById(@PathVariable("id") Long id) {
        
        ResponseEntity<GenericProductDto> responseEntity = new ResponseEntity<GenericProductDto>(productservice.deleteProductbyId(id), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        //return "Created new product with id : " + UUID.randomUUID();
        //return "created ned product with name : "+ productDto.getTitle();
        return productservice.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto product, @PathVariable("id") Long id) {
       return  productservice.updateProductById(product , id);
    }


}
