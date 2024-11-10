package de.slash.cartsservice.cart;

import de.slash.cartsservice.cartitem.CartItemDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {

    private Long id;

    private Long userId;

    private List<CartItemDTO> items = new ArrayList<>();
}
