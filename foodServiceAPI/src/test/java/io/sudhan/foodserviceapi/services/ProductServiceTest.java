package io.sudhan.foodserviceapi.services;

import io.sudhan.foodserviceapi.dataAccess.ProductCategoryRepository;
import io.sudhan.foodserviceapi.dataAccess.ProductRepository;
import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.models.ProductCategory;
import io.sudhan.foodserviceapi.services.Impl.ProductServiceImple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private static Product testProduct;
    private static List<Product> productList;
    private UUID existingId = UUID.fromString("a1b9b31d-e73c-4112-af7c-b68530f38222");
    private UUID nonExistId = UUID.fromString("a1b9b31d-e73c-4112-0000-b68530f38222");


    @Mock
    private ProductRepository repository;
    private ProductCategoryRepository categoryRepository;

    @InjectMocks
//    @Autowired
    private ProductServiceImple service;


    @BeforeAll
    public static void setup(){

        testProduct = new Product();
        testProduct.setId(UUID.fromString("a1b9b31d-e73c-4112-af7c-b68530f38222"));
        testProduct.setName("Apple");
        testProduct.setCategory(new ProductCategory(new Long(1), "FRUITS"));
        testProduct.setPrice(BigDecimal.valueOf(100.0));
        testProduct.setQuantity(100);

        productList = new ArrayList<>();
        productList.add(testProduct);

//        productList.add(
//                new Product(
//                        UUID.randomUUID(),
//                        "PEACH",
//                        "Peach test description",
//                        BigDecimal.valueOf(80.0),
//                        20,
//                        "Test URL",
//                        ProductCategory.FRUITS
//                )
//        );
//        productList.add(
//                new Product(
//                        UUID.randomUUID(),
//                        "Salman",
//                        "Test description",
//                        BigDecimal.valueOf(20.0),
//                        20,
//                        "Test URL",
//                        ProductCategory.FISH_AND_SEAFOOD
//                )
//        );
//        productList.add(
//                new Product(
//                        UUID.randomUUID(),
//                        "Almond Nuts",
//                        "Test description",
//                        BigDecimal.valueOf(20.0),
//                        20,
//                        "Test URL",
//                        ProductCategory.GRAINS_BEANS_AND_NUTS
//                )
//        );



    }

    @Test
    @DisplayName("Saving a new product")
    public void addProduct() {
        given(repository.save(testProduct)).willReturn(testProduct);

        Product result = service.add(testProduct);

        assertThat(result).isNotNull();
        assertThat(result.getCategory()).isEqualTo(testProduct.getCategory());
        assertThat(result.getName()).isEqualTo(testProduct.getName());
        assertThat(result.getPrice()).isEqualTo(testProduct.getPrice());
    }

    @Test
    @DisplayName("Get exist product by ID")
    public void getProductWithExistId() {
        given(repository.findById(testProduct.getId())).willReturn(Optional.of(testProduct));

        Optional<Product> result = service.get(testProduct.getId());

        assertThat(result).isNotNull();
        assertThat(result.get().getName()).isEqualTo(testProduct.getName());
        assertThat(result.get().getId()).isEqualTo(testProduct.getId());
    }


    @Test
    @DisplayName("Get non exist product by ID")
    public void getProductWithoutExistId() {
        given(repository.findById(nonExistId)).willReturn(Optional.empty());

        Optional<Product> result = service.get(nonExistId);

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
    }


    @Test
    @DisplayName("Get products list for specific category")
    public void getProductsByCategory() {
        given(repository.findAllByCategory_Id(testProduct.getCategory().getId())).willReturn(productList);

        Iterable<Product> result = service.getAllByCategory(testProduct.getCategory().getId());

        assertThat(result).isNotNull();
        for(Product resultProduct: result){
            assertThat(resultProduct.getCategory()).isEqualTo(testProduct.getCategory());
        }
    }



    @Test
    @DisplayName("Update the product")
    public void updateProduct() {
        given(repository.save(testProduct)).willReturn(testProduct);

        BigDecimal updatedPrice = BigDecimal.valueOf(200.0);
        String updatedName = "Orange";
        testProduct.setName(updatedName);
        testProduct.setPrice(updatedPrice);

        Product result = service.update(testProduct);
        System.out.println("It results");
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testProduct.getId());
        assertThat(result.getPrice()).isEqualTo(updatedPrice);
        assertThat(result.getName()).isEqualTo(updatedName);
    }








}
