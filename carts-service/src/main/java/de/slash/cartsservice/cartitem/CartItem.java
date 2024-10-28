package de.slash.cartsservice.cartitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.slash.cartsservice.cart.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_generator")
    @SequenceGenerator(name = "cart_item_id_generator", sequenceName = "cart_item_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonIgnore
    private Cart cart;
}
