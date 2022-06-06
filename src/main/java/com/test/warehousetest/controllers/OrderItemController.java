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
    public OrderItem getOrderItem(@PathVariable long id){
        return repository.findById(id).orElse(null);
    }

    @PostMapping(value = "/orderItem",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderItem addOrderItem(@RequestBody OrderItem order){
        return repository.save(order);
    }

    @PutMapping(value = "/orderItem/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderItem updateOrderItem(@PathVariable long id,
                                @RequestBody OrderItem order){
        OrderItem saved = repository.getById(id);
        saved.setProduct(order.getProduct());
        saved.setQuantity(order.getQuantity());
        return repository.save(saved);
    }

    @GetMapping(value = "/orderItems",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderItem> getAllOrderItems(){
        return repository.findAll();
    }

    @DeleteMapping(value = "/orderItem/{id}")
    public String deleteOrderItem(@PathVariable long id){
        repository.deleteById(id);
        return "Deleted";
    }

}
