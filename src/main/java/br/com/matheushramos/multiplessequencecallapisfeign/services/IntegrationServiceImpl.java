package br.com.matheushramos.multiplessequencecallapisfeign.services;

import br.com.matheushramos.multiplessequencecallapisfeign.controllers.dto.ProductIntegrationRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.dto.IntegrationSteps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.matheushramos.multiplessequencecallapisfeign.util.RetryAttemptsUtil.tryExecute;

@Slf4j
@Service
public class IntegrationServiceImpl {

    @Autowired
    private CategoryIntegrationServiceImpl categoryIntegrationService;

    @Autowired
    private ProductIntegrationServiceImpl productIntegrationService;

    @Autowired
    private StockIntegrationServiceImpl stockIntegrationService;

    public void executeIntegration(ProductIntegrationRequest productRequest) {
        var integrationSteps = new IntegrationSteps();
        integrationSteps.setProductIntegrationRequest(productRequest);

        tryExecute(() -> this.categoryIntegrationService.execute(integrationSteps))
                .flatMap(categoryResponse -> {
                    log.info("Success at integrate with category API.");
                    integrationSteps.setCategoryResponse(categoryResponse);
                    return tryExecute(() -> this.productIntegrationService.execute(integrationSteps));
                })
                .flatMap(productResponse -> {
                    log.info("Success at integrate with product API.");
                    integrationSteps.setProductResponse(productResponse);
                    return tryExecute(() -> this.stockIntegrationService.execute(integrationSteps));
                })
                .flatMap(stockResponse -> {
                    log.info("Success at integrate with stock API.");
                    return Optional.empty();
                });
    }

}
