package io.sudhan.foodserviceapi.services.Impl;

import io.sudhan.foodserviceapi.dataAccess.ProductCategoryRepository;
import io.sudhan.foodserviceapi.dataAccess.ProductRepository;
import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.models.ProductCategory;
import io.sudhan.foodserviceapi.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImple implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImple.class);

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product add(Product product) {
        if (product.isValid()){
            return productRepository.save(product);
        }
        // TODO: Raise the exception
        return null;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> get(UUID productId) {
        return productRepository.findById(productId);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Product> toDeleteProduct = productRepository.findById(id);
        if (toDeleteProduct.isPresent()){
            productRepository.delete(toDeleteProduct.get());
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Product> getAllByCategory(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

}
