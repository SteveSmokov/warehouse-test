package com.test.warehousetest.controllers;

import com.test.warehousetest.models.Product;
import com.test.warehousetest.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductPrice(@PathVariable("id") long id,
                                                      @Validated @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @PatchMapping(value = "/{id}/name/{name}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductName(@PathVariable("id") long id,
                                                     @PathVariable("name") String name){
        return ResponseEntity.ok(productService.updateProductName(id, name));
    }

    @PatchMapping(value = "/{id}/sku/{sku}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductSku(@PathVariable("id") long id,
                                                     @PathVariable("sku") String sku){
        return ResponseEntity.ok(productService.updateProductSku(id, sku));
    }

    @PatchMapping(value = "/{id}/price/{price}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProductPrice(@PathVariable("id") long id,
                                                    @PathVariable("price") BigDecimal price){
        return ResponseEntity.ok(productService.updateProductPrice(id, price));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID - "+id+" deleted successfully");
    }
}
