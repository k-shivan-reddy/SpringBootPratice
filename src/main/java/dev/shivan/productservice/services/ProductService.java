package dev.shivan.productservice.services;

import org.springframework.stereotype.Service;


import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.exceptions.NotFoundException;

import java.util.List;

@Service
public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductbyId(Long id);
}
