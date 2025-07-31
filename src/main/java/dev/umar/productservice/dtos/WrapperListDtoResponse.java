package dev.umar.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperListDtoResponse extends WrapperDto{
    private List<WrapperDto> Products;
}
