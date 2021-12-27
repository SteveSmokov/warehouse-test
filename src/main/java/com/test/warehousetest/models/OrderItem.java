package com.test.warehousetest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Id
    private Long id;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name="ord_id", nullable=false)
    private Order order;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prd_id", referencedColumnName = "id")
    private Product product;
}
