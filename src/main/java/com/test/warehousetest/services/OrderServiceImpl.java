package com.test.warehousetest.services;

import com.test.warehousetest.dao.OrderItemRepository;
import com.test.warehousetest.dao.OrderRepository;
import com.test.warehousetest.dao.ProductRepository;
import com.test.warehousetest.exceptions.ResourceNotFoundException;
import com.test.warehousetest.models.Order;
import com.test.warehousetest.models.OrderItem;
import com.test.warehousetest.models.Product;
import com.test.warehousetest.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final String EXCEPTION_ORDER_MESSAGE = "Order with ID - %o not found";
    private final String EXCEPTION_ITEM_MESSAGE = "Item with ID - %o not found";
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public OrderItem getOrderItem(long orderId, long id) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,orderId)));
        return  order.getOrderItems().stream().filter(o -> o.getId().equals(id)).findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ITEM_MESSAGE,id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItems(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,orderId)));
        return  order.getOrderItems();
    }

    private BigDecimal countAmount(List<OrderItem> items){
        BigDecimal result = BigDecimal.ZERO;
        for (OrderItem item:
             items) {
            if (item.getProduct().getPrice() == null) {
                item.setProduct(productRepository.findAnyById(item.getProduct().getId()).orElse(null));
            }
            BigDecimal priceForItem = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            result = result.add(priceForItem);
            System.out.println(result);
        }
        return result;
    }

    @Override
    @Transactional
    public OrderItem addOrderItem(long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,orderId)));
        order.getOrderItems().add(orderItem);
        order.setAmount(countAmount(order.getOrderItems()));
        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem updateQuantityInOrderItem(long orderId, long id, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,orderId)));
        OrderItem orderItem = order.getOrderItems().stream().filter(o -> o.getId().equals(id)).findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ITEM_MESSAGE,id)));
        orderItem.setQuantity(quantity);
        order.setAmount(countAmount(order.getOrderItems()));
        orderRepository.save(order);
        return  orderItem;
    }

    @Override
    @Transactional
    public void deleteOrderItemIntoOrder(long orderId, long id) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,orderId)));
        order.getOrderItems().removeIf(i -> i.getId().equals(id));
        order.setAmount(countAmount(order.getOrderItems()));
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format(EXCEPTION_ORDER_MESSAGE,id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order addOrder(Order order) {
        order.setAmount(countAmount(order.getOrderItems()));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
}
