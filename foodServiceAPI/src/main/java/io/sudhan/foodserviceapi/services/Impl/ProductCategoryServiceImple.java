package io.sudhan.foodserviceapi.services.Impl;

import io.sudhan.foodserviceapi.dataAccess.ProductCategoryRepository;
import io.sudhan.foodserviceapi.models.ProductCategory;
import io.sudhan.foodserviceapi.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryServiceImple implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<ProductCategory> getById(Long id) {
        return this.repository.findProductCategoryById(id);
    }

    @Override
    public Optional<ProductCategory> getByName(String name) {
        return this.repository.findProductCategoryByName(name);
    }
}
