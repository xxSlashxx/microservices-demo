package de.slash.cartsservice.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;
}
