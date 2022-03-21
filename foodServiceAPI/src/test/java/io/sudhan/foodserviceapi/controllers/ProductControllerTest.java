package io.sudhan.foodserviceapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sudhan.foodserviceapi.models.Product;
import io.sudhan.foodserviceapi.services.ProductService;
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

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @BeforeEach
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

//    @Test
//    public void getProduct() throws Exception{
//
//        productService.add(product);
//
//        given(productService.get(product.getId())).willReturn(Optional.of(product));
//
//        mockMvc.getDispatcherServlet().getHandlerMappings();
//        ResultActions results = mockMvc.perform(
//                get("/food-items/{id}", "ef8e7f6f-858b-4c14-82d5-00e98eb6474b")
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        results.andExpect(status().isOk());
//
//
//    }



}
