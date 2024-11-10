package de.slash.cartsservice.app;

import de.slash.cartsservice.cart.Cart;
import de.slash.cartsservice.cart.CartDTO;
import de.slash.cartsservice.cart.CartService;
import de.slash.cartsservice.product.ProductDTO;
import de.slash.cartsservice.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create/{userId}")
    private ResponseEntity<CartDTO> createCart(@PathVariable("userId") Long userId) {
        Cart createdCart = cartService.createCart(userId);
        CartDTO cartDTO = modelMapper.map(createdCart, CartDTO.class);
        return ResponseEntity.status(OK).body(cartDTO);
    }

    @PostMapping("/{userId}/products/{productId}")
    private ResponseEntity<CartDTO> addProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        ProductDTO productDTO = productService.loadById(productId);
        Cart currentCart = cartService.loadByUserId(userId);
        Cart updatedCart = cartService.addProduct(currentCart, productDTO.getId());
        CartDTO cartDTO = modelMapper.map(updatedCart, CartDTO.class);
        return ResponseEntity.status(OK).body(cartDTO);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    private ResponseEntity<CartDTO> removeProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        ProductDTO productDTO = productService.loadById(productId);
        Cart currentCart = cartService.loadByUserId(userId);
        Cart updatedCart = cartService.removeProduct(currentCart, productDTO.getId());
        CartDTO cartDTO = modelMapper.map(updatedCart, CartDTO.class);
        return ResponseEntity.status(OK).body(cartDTO);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<CartDTO> getCart(@PathVariable("userId") Long userId) {
        Cart currentCart = cartService.loadByUserId(userId);
        CartDTO cartDTO = modelMapper.map(currentCart, CartDTO.class);
        return ResponseEntity.status(OK).body(cartDTO);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<String> deleteCart(@PathVariable("userId") Long userId) {
        Cart cart = cartService.loadByUserId(userId);
        cartService.deleteCart(cart);
        return ResponseEntity.status(OK).build();
    }
}
