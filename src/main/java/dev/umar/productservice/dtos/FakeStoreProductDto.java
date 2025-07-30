package dev.umar.productservice.dtos;

import dev.umar.productservice.models.Category;
import dev.umar.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    //Converting the fakestoredto into the product
    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDesctiption(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category category1 = new Category();
        category1.setTitle(category);
        product.setCategory(category1);
        return product;
    }
}
