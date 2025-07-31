package dev.umar.productservice.services;

import dev.umar.productservice.dtos.FakeStoreProductDto;
import dev.umar.productservice.dtos.WrapperDto;
import dev.umar.productservice.dtos.WrapperListDtoResponse;
import dev.umar.productservice.models.Category;
import dev.umar.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
//this annotation is for telling spring hey spring this is a service class where business logic is going to run
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
   // @Override
  //  public Product getAllProducts() {
    //    return null;
   // }

    @Override
    public List<Product> getAllProducts() {
        WrapperListDtoResponse response = restTemplate.getForObject("https://dummyjson.com/products",
                WrapperListDtoResponse.class);
        List<Product> products = new ArrayList<>();
        for(WrapperDto dto : response.getProducts()){
            Product product = new Product();
            product.setId(dto.getId());
            product.setTitle(dto.getTitle());
            product.setDesctiption(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImageUrl(dto.getImage());
            Category category = new Category();
            category.setTitle(dto.getCategory());
            product.setCategory(category);
            products.add(product);
        }
        return products;
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
        FakeStoreProductDto response = restTemplate.postForObject("https://dummyjson.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id) {
       FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://dummyjson.com/products/"+ id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }
}
