package com.test.warehousetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@DynamicUpdate
@DynamicInsert
public class Product implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "sku", length = 30)
    private String sku;
    @NonNull
    @Column(name = "name", length = 500)
    private String name;
    @JsonIgnore
    @Column(name = "active")
    private Boolean active = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cat_id")
    private Category category;

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
