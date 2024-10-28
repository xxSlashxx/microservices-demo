package de.slash.cartsservice.cartitem;

import de.slash.cartsservice.cart.Cart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem createCartItem(Cart cart, Long productId) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setCart(cart);
        return cartItem;
    }

    public CartItem loadCartItem(Cart cart, Long productId) {
        return cartItemRepository.findByCartAndProductId(cart, productId);
    }

    @KafkaListener(topics = "${de.slash.kafka.topic.product.delete}")
    public void listenToProductDelete(Long productId) {
        cartItemRepository.deleteByProductId(productId);
    }
}
