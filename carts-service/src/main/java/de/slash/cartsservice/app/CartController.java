package de.slash.cartsservice.app;

import de.slash.cartsservice.cart.Cart;
import de.slash.cartsservice.cart.CartService;
import de.slash.cartsservice.product.Product;
import de.slash.cartsservice.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/create/{userId}")
    private ResponseEntity<Cart> createCart(@PathVariable("userId") Long userId) {
        Cart createdCart = cartService.createCart(userId);
        return ResponseEntity.status(OK).body(createdCart);
    }

    @PostMapping("/{userId}/products/{productId}")
    private ResponseEntity<Cart> addProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Product product = productService.loadById(productId);
        Cart currentCart = cartService.loadByUserId(userId);
        Cart updatedCart = cartService.addProduct(currentCart, product);
        return ResponseEntity.status(OK).body(updatedCart);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    private ResponseEntity<Cart> removeProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Product product = productService.loadById(productId);
        Cart currentCart = cartService.loadByUserId(userId);
        Cart updatedCart = cartService.removeProduct(currentCart, product);
        return ResponseEntity.status(OK).body(updatedCart);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<Cart> getCart(@PathVariable("userId") Long userId) {
        Cart currentCart = cartService.loadByUserId(userId);
        return ResponseEntity.status(OK).body(currentCart);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<String> deleteCart(@PathVariable("userId") Long userId) {
        Cart cart = cartService.loadByUserId(userId);
        cartService.deleteCart(cart);
        return ResponseEntity.status(OK).build();
    }
}
