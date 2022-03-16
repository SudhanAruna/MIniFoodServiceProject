package io.sudhan.foodserviceapi.dataAccess;

import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findProductCategoryById(Long id);

    Optional<ProductCategory> findProductCategoryByName(String name);
}
