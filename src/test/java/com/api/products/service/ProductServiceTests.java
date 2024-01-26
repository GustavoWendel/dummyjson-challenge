package com.api.products.service;

import com.api.products.application.service.ProductService;
import com.api.products.application.service.exceptions.NotFoundException;
import com.api.products.infra.clients.ProductFeignClient;
import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import com.api.products.tests.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.api.products.tests.Factory.createProductResponse;
import static com.api.products.tests.Factory.createProductResponseWrapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductFeignClient productFeignClient;


    @Test
    void testGetAllProductsSuccess() {
        Integer skip = 0;
        Integer limit = 30;
        ProductResponseWrapper expectedResponse = createProductResponseWrapper();

        ResponseEntity<ProductResponseWrapper> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(productFeignClient.getAll(skip, limit)).thenReturn(responseEntity.getBody());

        ProductResponseWrapper actualResponse = service.getAllProducts(skip, limit);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetAllProductsNullResponse() {
        Integer skip = 0;
        Integer limit = 30;

        when(productFeignClient.getAll(skip, limit)).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> service.getAllProducts(skip, limit));
    }

    @Test
    void testGetAllProductsNullProductList() {
        Integer skip = 0;
        Integer limit = 30;
        ProductResponseWrapper responseWrapper = createProductResponseWrapper();

        responseWrapper.setProducts(null);
        when(productFeignClient.getAll(skip, limit)).thenReturn(responseWrapper);

        assertThrows(IllegalStateException.class, () -> service.getAllProducts(skip, limit));
    }

    @Test
    void testFindByIdSuccess() {
        Integer productId = 1;
        ProductResponse response = createProductResponse();
        response.setId(productId);

        when(productFeignClient.findById(productId)).thenReturn(response);

        ProductResponse result = service.findById(productId);

        assertEquals(productId, result.getId());
    }

    @Test
    void testFindByIdWithNullResponse() {
        Integer productId = 1;

        when(productFeignClient.findById(productId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.findById(productId));
    }

    @Test
    void testSearchProductsWithValidQuery() {
        // Arrange
        String query = "validQuery";
        Integer skip = 0;
        Integer limit = 10;
        ProductResponseWrapper response = createProductResponseWrapper();

        when(productFeignClient.searchProducts(query, skip, limit)).thenReturn(response);

        ProductResponseWrapper responseWrapper = service.searchProducts(query, skip, limit);

        assertNotNull(responseWrapper);
    }

    @Test
    void testSearchProductsWithInvalidQuery() {
        String invalidQuery = "invalidQuery";
        Integer skip = 0;
        Integer limit = 10;

        when(productFeignClient.searchProducts(invalidQuery, skip, limit)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.searchProducts(invalidQuery, skip, limit));
    }
}
