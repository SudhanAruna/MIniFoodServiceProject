package io.sudhan.foodserviceapi.controllers;

import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.models.ProductCategory;
import io.sudhan.foodserviceapi.services.ProductCategoryService;
import io.sudhan.foodserviceapi.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.EnumUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3031")
@RestController
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/food-items")
    public ResponseEntity<?> getFoodItems(){
        logger.warn("Request received for get all food item products");
        Iterable<Product> allProducts = productService.getAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/food-items/{id}")
    public ResponseEntity<?> getFoodItemById(@PathVariable(name="id") UUID id){
        Optional<Product> product = this.productService.get(id);
        if (product.isPresent())
            return ResponseEntity.ok(product.get());

        logger.debug("Requested food item doesn't exist");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/food-items/categories")
    public ResponseEntity<?> getFoodItemCategories(){
        // Checks if provided category is valid
        List<ProductCategory> productCategories = productCategoryService.getAll();
        if (!productCategories.isEmpty()){
            return ResponseEntity.ok(productCategories);
        }
        logger.debug("Requested category doesn't exist");
        return ResponseEntity.badRequest().build();
    }

// TODO: THIS FOLLOWING DOESN'T WORK SO CHECK ON THIS
    @GetMapping("/food-items/categories/{id}")
    public ResponseEntity<?> getFoodItemByCategory(@PathVariable(name="id") Long id){
        // Checks if provided category is valid
        Optional<ProductCategory> productCategory = productCategoryService.getById(id);

        if (productCategory.isPresent()){
            List<Product> productListByCategory = this.productService.findByCategory(productCategory.get());
            return ResponseEntity.ok(productListByCategory);
        }
        logger.debug("Requested category doesn't exist");
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/food-items")
    public ResponseEntity<?> addFoodItem(@RequestBody Product product, HttpServletRequest request) {
        if (product.isValid()){
            product = productService.add(product);
            return ResponseEntity.ok(product);
        }

        logger.debug("Request from user is not valid");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/food-items/")
    public ResponseEntity<?> updateFoodItem(@PathVariable("id") UUID id, @RequestBody Product product, HttpServletRequest request){
        // Check if food-item is valid
        if (!product.isValid()) {
            logger.debug("Request from user is not valid");
            return ResponseEntity.badRequest().build();
        }
        Optional<Product> existFoodItem = productService.get(id);

        // Validate if food-item exist with given ID
        if (!existFoodItem.isPresent()) {
            logger.debug("Requested food item doesn't exist");
            return ResponseEntity.notFound().build();
        }
        product.setId(existFoodItem.get().getId());
        product = productService.update(product);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/food-items/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable("id") UUID id, HttpServletRequest request){
        Optional<Product> existFoodItem = productService.get(id);
        // Check if food item exist
        if (!existFoodItem.isPresent()) {
            logger.debug("Given food item id doesn't exist");
            return ResponseEntity.badRequest().build();
        }

        if (productService.delete(id)) {
            logger.debug("Successfully deleted the food item");
            return ResponseEntity.accepted().build();
        }
        logger.warn("Failed to delete the existing food item");
        return ResponseEntity.internalServerError().build();
    }

}
