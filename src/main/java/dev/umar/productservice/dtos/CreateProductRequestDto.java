package dev.umar.productservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateProductRequestDto {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
