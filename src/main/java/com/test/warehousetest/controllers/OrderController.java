package com.test.warehousetest.controllers;


import com.test.warehousetest.dao.OrderRepository;
import com.test.warehousetest.models.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    @Autowired
    private OrderRepository repository;

    @GetMapping(value = "/order/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getCategory(@PathVariable long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/order",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Order addCategory(@RequestBody Order order){
        return repository.save(order);
    }

    @PutMapping(value = "/order/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order updateCategory(@PathVariable long id,
                                @RequestBody Order order){
        Order saved = repository.getById(id);
        saved.setAmount(order.getAmount());
        saved.setOrderItems(order.getOrderItems());
        return repository.save(saved);
    }

    @GetMapping(value = "/orders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllCategories(){
        return repository.findAll();
    }
}
