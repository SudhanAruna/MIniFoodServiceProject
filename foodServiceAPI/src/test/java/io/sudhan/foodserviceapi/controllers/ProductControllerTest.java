package io.sudhan.foodserviceapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Autowired
    private ProductController productController;

    private Product product;

    private static final String URI = "/food-items";



    @BeforeAll
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
//                .setControllerAdvice()
                .build();

        product = new Product();
        product.setId(UUID.fromString("ef8e7f6f-858b-4c14-82d5-00e98eb6474b"));
        product.setName("Name");
        product.setPrice(new BigDecimal(99.00));
        product.setQuantity(20);

//        productService.add(product);
    }

    // TODO: Complete the test createProduct method
//    @Test
//    @DisplayName("Returns created product")
//    public void createProduct() throws Exception {
//        given(productService.add(product)).willReturn(product);
//
//        ResultActions result = mockMvc.perform(
//                post("/food-items")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(product)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//    }



}
