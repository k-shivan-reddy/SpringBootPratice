package dev.shivan.productservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import dev.shivan.productservice.Model.Product;
import dev.shivan.productservice.dtos.GenericProductDto;
import dev.shivan.productservice.exceptions.NotFoundException;
import dev.shivan.productservice.dtos.FakeStoreProductDto;

@Service("fakestoreserviceImpl")
public class FakeStoreProductService implements ProductService{


    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private GenericProductDto converfakeStoretoGeneric(FakeStoreProductDto fakeStoreProductDto)
    {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
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
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        // If we create  a service like this we will not get the same instance what 
        // Spring would have initialised 
        //        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto == null){
            throw  new NotFoundException("this is not found id" + id);
        }
        return converfakeStoretoGeneric(fakeStoreProductDto);
    }
    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(createProductRequestUrl, FakeStoreProductDto[].class);

        List<GenericProductDto> answer = new ArrayList<>();

        for ( FakeStoreProductDto fakeStoreProductDto: response.getBody()) {
            answer.add(converfakeStoretoGeneric(fakeStoreProductDto));
        }
        return answer;
    }
    public GenericProductDto deleteProductbyId(Long id)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductRequestUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        return converfakeStoretoGeneric(fakeStoreProductDto);
    }
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, Long id)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto,FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductRequestUrl,HttpMethod.PUT,requestCallback,responseExtractor,id);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        return converfakeStoretoGeneric(fakeStoreProductDto);
    }
}