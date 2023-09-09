package dev.shivan.productservice.services;

import org.springframework.stereotype.Service;

import dev.shivan.productservice.Model.Product;
import dev.shivan.productservice.dtos.GenericProductDto;

@Service
public interface ProductService {
    GenericProductDto getProductById(Long id);
}
