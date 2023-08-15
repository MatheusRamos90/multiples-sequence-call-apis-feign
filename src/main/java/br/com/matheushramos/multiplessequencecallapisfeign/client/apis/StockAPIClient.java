package br.com.matheushramos.multiplessequencecallapisfeign.client.apis;

import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stockAPI", url = "${mock-apis.host}")
public interface StockAPIClient {

    @PostMapping("/stock")
    StockResponse createStock(@RequestBody StockRequest request);

}
