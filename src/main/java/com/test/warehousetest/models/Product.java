package com.test.warehousetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "sku", length = 30)
    @Field(type = FieldType.Text)
    private String sku;
    @Column(name = "name", length = 500)
    @Field(type = FieldType.Text)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cat_id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")//, cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private List<OrderItem> orderItems;
}
