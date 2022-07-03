package com.test.warehousetest.services;

import com.test.warehousetest.models.Order;
import com.test.warehousetest.models.OrderItem;

import java.util.List;

public interface OrderService {

    Order getOrder(long id);
    List<Order> getOrders();
    Order addOrder(Order order);
    void deleteOrder(long id);
    OrderItem getOrderItem(long orderId, long id);
    List<OrderItem> getOrderItems(long orderId);
    OrderItem addOrderItem(long orderId, OrderItem orderItem);
    OrderItem updateQuantityInOrderItem(long orderId, long id, int quantity);
    void deleteOrderItemIntoOrder(long orderId, long id);
}
