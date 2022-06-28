package com.test.warehousetest.services;

import com.test.warehousetest.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> getProducts();
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    Product updateProductName(Long id, String name);
    Product updateProductSku(Long id, String sku);
    Product updateProductPrice(Long id, BigDecimal price);
    void deleteProduct(Long id);
}
