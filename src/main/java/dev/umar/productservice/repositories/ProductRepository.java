package dev.umar.productservice.repositories;

import dev.umar.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>{
   Product findByTitle(String title);

}
