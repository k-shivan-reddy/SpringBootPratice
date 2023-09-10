package dev.shivan.productservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import dev.shivan.productservice.Model.Product;
import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.dtos.FakeStoreProductDto;

@Service("fakestoreserviceImpl")
public class FakeStoreProductService implements ProductService{


    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public GenericProductDto createProduct(GenericProductDto genericProductDto)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response =
        restTemplate.postForEntity(createProductRequestUrl, genericProductDto, GenericProductDto.class);

        return response.getBody();
    }
    public GenericProductDto getProductById(Long id){
        // If we create  a service like this we will not get the same instance what 
        // Spring would have initialised 
        //        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        //        response.getStatusCode()

        return product;
        //        return null;
    }
}
