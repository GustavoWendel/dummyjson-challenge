package com.api.products.application.resources;

import com.api.products.response.ProductResponse;
import com.api.products.response.ProductResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductControllerApi {

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponseWrapper.class)) })
    })
    @GetMapping
    ResponseEntity<ProductResponseWrapper> getAll(
            @Parameter(description = "Number of items to skip")
            @RequestParam(value = "skip", defaultValue = "0") Integer skip,
            @Parameter(description = "Maximum number of items to return")
            @RequestParam(value = "limit", defaultValue = "30") Integer limit);

    @Operation(summary = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product by ID",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> getById(
            @Parameter(description = "ID of the product to be retrieved")
            @PathVariable Integer id);

    @Operation(summary = "Search products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products by search",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductResponseWrapper.class)) })
    })
    @GetMapping("/search")
    ResponseEntity<ProductResponseWrapper> search(
            @Parameter(description = "Query string for searching products")
            @RequestParam("q") String query,
            @Parameter(description = "Number of items to skip")
            @RequestParam(value = "skip", defaultValue = "0") Integer skip,
            @Parameter(description = "Maximum number of items to return")
            @RequestParam(value = "limit", defaultValue = "30") Integer limit);
}

