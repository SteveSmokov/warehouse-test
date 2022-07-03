package com.test.warehousetest.services;

import com.test.warehousetest.dao.ProductRepository;
import com.test.warehousetest.exceptions.ResourceNotFoundException;
import com.test.warehousetest.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final String EXCEPTION_MESSAGE = "Product with ID - %o not found";
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        product.setId(0);
        return productRepository.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product updateProduct(Long id, Product product) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        productRepository.deleteById(id);
        savedProduct.setId(0);
        savedProduct.setName(product.getName());
        savedProduct.setSku(product.getSku());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setCategory(product.getCategory());
        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        productRepository.delete(product);
    }

    @Override
    @SneakyThrows
    public Product updateProductName(Long id, String name) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        productRepository.deleteById(id);
        Product newProduct = savedProduct.clone();
        newProduct.setId(0);
        newProduct.setName(name);
        return productRepository.save(newProduct);
    }

    @Override
    @SneakyThrows
    public Product updateProductSku(Long id, String sku) {
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        productRepository.deleteById(id);
        Product newProduct = savedProduct.clone();
        newProduct.setId(0);
        newProduct.setSku(sku);
        return productRepository.save(newProduct);
    }

    @Override
    @SneakyThrows
    public Product updateProductPrice(Long id, BigDecimal price){
        Product savedProduct = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_MESSAGE,id)));
        productRepository.deleteById(id);
        Product newProduct = savedProduct.clone();
        newProduct.setId(0);
        newProduct.setPrice(price);
        return productRepository.save(newProduct);
    }
}
