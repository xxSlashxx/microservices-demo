package de.slash.productsservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Value("${de.slash.kafka.topic.product.delete}")
    private String topicDelete;

    private final ProductRepository productRepository;

    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, KafkaTemplate<String, Long> kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found.", id)));
    }

    public Product createProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
        kafkaTemplate.send(topicDelete, product.getId());
    }
}
