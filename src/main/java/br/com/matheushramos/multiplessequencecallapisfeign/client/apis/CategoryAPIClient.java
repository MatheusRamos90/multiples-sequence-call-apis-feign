package br.com.matheushramos.multiplessequencecallapisfeign.client.apis;

import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "categoryAPI", url = "${mock-apis.host}")
public interface CategoryAPIClient {

    @PostMapping("/category")
    CategoryResponse createCategory(@RequestBody CategoryRequest request);

}
