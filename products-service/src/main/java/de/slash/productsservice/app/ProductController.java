package de.slash.productsservice.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import de.slash.productsservice.product.Product;
import de.slash.productsservice.product.ProductDTO;
import de.slash.productsservice.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    private ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product createdProduct = productService.createProduct(productDTO.getName(), productDTO.getPrice());
        return ResponseEntity.status(OK).body(createdProduct);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.status(OK).body(product);
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json-patch+json")
    private ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Product product = productService.getById(id);
            Product patchedProduct = applyPatch(patch, product);
            Product savedProduct = productService.saveProduct(patchedProduct);
            return ResponseEntity.status(OK).body(savedProduct);
        } catch (JsonPatchException | JsonProcessingException ex) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    private Product applyPatch(JsonPatch patch, Product product) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
        return objectMapper.treeToValue(patched, Product.class);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        productService.deleteProduct(product);
        return ResponseEntity.status(OK).build();
    }
}
