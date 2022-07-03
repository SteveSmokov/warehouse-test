package com.test.warehousetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    @NotEmpty(
            message = "Product sku is obligatory"
    )
    @Size(
            min = 4,
            max = 100,
            message = "Product sku should have at least 4 to 100 characters")
    @Column(name = "sku", length = 30)
    private String sku;
    @NotEmpty(
            message = "Product name is obligatory"
    )
    @Size(
            min = 4,
            max = 500,
            message = "Product name should have at least 4 to 500 characters")
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
