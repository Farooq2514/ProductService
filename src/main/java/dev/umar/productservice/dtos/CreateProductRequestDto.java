package dev.umar.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class CreateProductRequestDto {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
