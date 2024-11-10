package de.slash.cartsservice.cart;

import de.slash.cartsservice.cartitem.CartItem;
import de.slash.cartsservice.cartitem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemService cartItemService;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    public Cart loadByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseThrow(() -> new CartNotFoundException(format("Cart with id %s not found.", userId)));
    }

    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }

    public Cart addProduct(Cart cart, Long productId) {
        CartItem cartItem = cartItemService.createCartItem(cart, productId);
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    public Cart removeProduct(Cart cart, Long productId) {
        CartItem cartItem = cartItemService.loadCartItem(cart, productId);
        cart.getItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
