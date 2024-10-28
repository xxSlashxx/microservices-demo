package de.slash.productsservice.product;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name="product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private BigDecimal price;
}
