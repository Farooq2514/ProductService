package dev.umar.productservice.services;

import dev.umar.productservice.dtos.FakeStoreProductDto;
import dev.umar.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
//this annotation is for telling spring hey spring this is a service class where business logic is going to run
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getAllProducts(int page, int size) {
        return List.of();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String image,
                                 String category,
                                 double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id) {
       FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }
}
