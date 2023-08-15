package br.com.matheushramos.multiplessequencecallapisfeign.services;

import br.com.matheushramos.multiplessequencecallapisfeign.client.apis.StockAPIClient;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.dto.IntegrationSteps;
import br.com.matheushramos.multiplessequencecallapisfeign.enums.EnumStatus;
import br.com.matheushramos.multiplessequencecallapisfeign.services.interfaces.IntegrationStepsInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockIntegrationServiceImpl implements IntegrationStepsInterface<StockResponse> {

    @Autowired
    private StockAPIClient stockAPI;

    @Override
    public StockResponse execute(IntegrationSteps integrationSteps) {
        log.info("Calling stock API.");
        return this.stockAPI.createStock(StockRequest.builder()
                .idCategory(integrationSteps.getCategoryResponse().getId())
                .idProduct(integrationSteps.getProductResponse().getId())
                .amountProduct(integrationSteps.getProductIntegrationRequest().getAmount())
                .status(EnumStatus.ATIVO)
                .build());
    }

}
