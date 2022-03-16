package io.sudhan.foodserviceapi.services;

import io.sudhan.foodserviceapi.models.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    List<ProductCategory> getAll();

    Optional<ProductCategory> getById(Long id);

    Optional<ProductCategory> getByName(String name);

}
