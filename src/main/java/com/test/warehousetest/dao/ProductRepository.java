package com.test.warehousetest.dao;

import com.test.warehousetest.models.Product;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends HistoryBaseRepository<Product, Long> {
}
