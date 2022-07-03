package com.test.warehousetest.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name", length = 500)
    @NotEmpty(message = "Category name is obligatory")
    @Size(min = 2, message = "Category name should have at least 2 characters")
    private String name;

    @OneToMany(mappedBy = "category")//, cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private List<Product> products;
}
