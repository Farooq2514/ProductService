package dev.umar.productservice.controllers;

import dev.umar.productservice.dtos.CreateProductRequestDto;
import dev.umar.productservice.models.Product;
import dev.umar.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {
    //Injecting the dependency
    ProductService productService;
    RestTemplate restTemplate;
    ProductController(ProductService productService , RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    //this is creating a product
    //it is going to create a post api
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request){
      return productService.createProduct(
                                        request.getTitle(),
                                        request.getDescription(),
                                        request.getImage(),
                                        request.getCategory(),
                                        request.getPrice()
                                        );
    }
    //fetching a single product by it's id or its path
    @GetMapping("/products/{id}")
    //here we are writing the path variable to say that hey spring there is path where we need to go and fetch by the id
    public Product getProductDetails(@PathVariable("id") long productId){
        return productService.getSingleProduct(productId);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
      return productService.getAllProducts();
    }
    public void updateProduct(){
    }
}
/*
Get all products
Get all categories
Update a product
Delete a product
Get products in a specific category
 */
