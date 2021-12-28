package com.test.warehousetest.controllers;


import com.test.warehousetest.dao.OrderItemRepository;
import com.test.warehousetest.models.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderItemController {
    @Autowired
    private OrderItemRepository repository;

    @GetMapping(value = "/orderItem/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderItem getCategory(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping(value = "/orderItem",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderItem addCategory(@RequestBody OrderItem order){
        return repository.save(order);
    }

    @PutMapping(value = "/orderItem/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderItem updateCategory(@PathVariable long id,
                                @RequestBody OrderItem order){
        OrderItem saved = repository.getById(id);
        saved.setProduct(order.getProduct());
        saved.setQuantity(order.getQuantity());
        return repository.save(saved);
    }

    @GetMapping(value = "/orderItems",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderItem> getAllCategories(){
        return repository.findAll();
    }
}
