package de.slash.productsservice.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;
}
