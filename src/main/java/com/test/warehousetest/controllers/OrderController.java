package com.test.warehousetest.controllers;

import com.test.warehousetest.models.Order;
import com.test.warehousetest.models.OrderItem;
import com.test.warehousetest.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Product with ID - "+id+" deleted successfully");
    }

    @GetMapping(value = "/{id}/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.getOrderItems(id));
    }

    @GetMapping(value = "/{id}/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderItem> getOrderItemByOrderId(@PathVariable("id") Long id,
                                                          @PathVariable("itemId") Long itemId){
        return ResponseEntity.ok(orderService.getOrderItem(id, itemId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(@Validated @RequestBody Order order){
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id,
                                             @Validated @RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

}
