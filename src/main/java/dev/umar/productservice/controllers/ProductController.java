package dev.umar.productservice.controllers;

import dev.umar.productservice.dtos.CreateProductRequestDto;
import dev.umar.productservice.dtos.ErrorDto;
import dev.umar.productservice.exceptions.ProductNotFoundException;
import dev.umar.productservice.models.Product;
import dev.umar.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {
    //Injecting the dependency
    ProductService productService;
    RestTemplate restTemplate;

    ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    //this is creating a product
    //it is going to create a post api
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
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
    public Product getProductDetails(@PathVariable("id") long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id , @RequestBody Product product) throws ProductNotFoundException {
      Product updatedProduct = productService.updateProduct(id , product);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//    }
    // this will work only for this particular class so we are making it global by shifting to the other class
    //so if the other class got the error but still we can easily use that
    // the above class is to handle if at all whenver we receive the error of product not found then immediately
    //we are going to send the message
}
/*
Get all products
Get all categories
Update a product
Delete a product
Get products in a specific category
 */
