package dev.shivan.productservice.services;

import org.springframework.stereotype.Service;

import dev.shivan.productservice.Model.Product;
import dev.shivan.productservice.dtos.GenericProductDto;

@Service("selfproductImpl")
public class SelfProductService implements ProductService{
    public GenericProductDto getProductById(Long id){
        return null;}
}
