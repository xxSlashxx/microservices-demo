package de.slash.cartsservice.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductService {

    @Value("${de.slash.products-service.service-id}")
    private String productsServiceServiceId;

    @Value("${de.slash.products-service.base-url}")
    private String productsServiceBaseUrl;

    private final DiscoveryClient discoveryClient;

    private final RestClient restClient;

    public ProductService(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }


    public ProductDTO loadById(Long id) {
        ServiceInstance serviceInstance = discoveryClient.getInstances(productsServiceServiceId).getFirst();
        //WebClient client = WebClient.create(serviceInstance.getScheme() + "://" + serviceInstance.getServiceId() + ":" + serviceInstance.getPort());
        //return client.get().uri(productsServiceBaseUrl + "/" + id).retrieve().bodyToFlux(ProductDTO.class).blockFirst();

        return restClient.get().uri(serviceInstance.getUri() + productsServiceBaseUrl + "/" + id).retrieve().body(ProductDTO.class);

    }
}
