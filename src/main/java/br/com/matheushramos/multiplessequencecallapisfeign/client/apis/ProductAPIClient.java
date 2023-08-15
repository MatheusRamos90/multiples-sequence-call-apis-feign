package br.com.matheushramos.multiplessequencecallapisfeign.client.apis;

import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productAPI", url = "${mock-apis.host}")
public interface ProductAPIClient {

    @PostMapping("/product")
    ProductResponse createProduct(@RequestBody ProductRequest request);

}
