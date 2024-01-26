package com.api.products.resources;

import com.api.products.application.service.ProductService;
import com.api.products.application.service.exceptions.NotFoundException;
import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import com.api.products.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static com.api.products.tests.Factory.createProductResponse;
import static com.api.products.tests.Factory.createProductResponseWrapper;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService service;
    @Autowired
    private ObjectMapper objectMapper;

    private Integer existingId;
    private Integer nonExistingId;
    private ProductResponse productResponse;

    @BeforeEach
    void setUpt() {
        existingId = 1;
        nonExistingId = 1000;
        productResponse = createProductResponse();

        when(service.findById(existingId)).thenReturn(productResponse);
        when(service.findById(nonExistingId)).thenThrow(NotFoundException.class);

    }

    @Test
    void findAllShouldReturnProducts() throws Exception {
        Integer skip = 0;
        Integer limit = 30;
        ProductResponseWrapper expectedResponse = createProductResponseWrapper();

        when(service.getAllProducts(skip, limit)).thenReturn(expectedResponse);

        mockMvc.perform(get("/v1/products")
                        .param("skip", skip.toString())
                        .param("limit", limit.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

        verify(service, times(1)).getAllProducts(skip, limit);
    }

    @Test
    void findByIdShouldReturnProductWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/v1/products/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
    }

    @Test
    void searchProductsShouldReturnPage() throws Exception {
        ProductResponseWrapper mockResponse = createProductResponseWrapper();

        when(service.searchProducts(anyString(), anyInt(), any())).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/products/search")
                        .param("q", "someQuery")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockResponse)));
    }
}
