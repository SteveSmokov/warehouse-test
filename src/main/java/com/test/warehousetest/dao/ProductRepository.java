package com.test.warehousetest.dao;

import com.test.warehousetest.models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope(value = "prototype")
public interface ProductRepository extends HistoryBaseRepository<Product, Long> {
}
