package dev.umar.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

}
