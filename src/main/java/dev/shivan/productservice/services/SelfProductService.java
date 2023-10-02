package dev.shivan.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.shivan.productservice.Model.Product;
import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.repositories.ProductRepository;

@Service("selfproductImpl")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public GenericProductDto getProductById(Long id){
        return null;
    }
        @Override
        public GenericProductDto createProduct(GenericProductDto product) {
            return null;
        } 
        @Override
        public List<GenericProductDto> getAllProducts() {
            return null;
        }
        public GenericProductDto deleteProductbyId(Long id)
        {
            return null;
        }
        public GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id)
        {
            return null;
        }
}
