package de.slash.cartsservice.cartitem;

import de.slash.cartsservice.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductId(Cart cart, Long productId);

    void deleteByProductId(Long productId);
}
