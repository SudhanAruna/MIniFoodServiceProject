package io.sudhan.foodserviceapi.services;

import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.models.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductService {

    Product add(Product product);

    Product update(Product product);

    Iterable<Product> getAll();

    Optional<Product> get(UUID id);

    boolean delete(UUID id);

    List<Product> findByCategory(ProductCategory productCategory);

}
