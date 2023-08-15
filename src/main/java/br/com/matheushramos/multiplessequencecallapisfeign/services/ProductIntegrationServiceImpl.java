package br.com.matheushramos.multiplessequencecallapisfeign.services;

import br.com.matheushramos.multiplessequencecallapisfeign.client.apis.ProductAPIClient;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.dto.IntegrationSteps;
import br.com.matheushramos.multiplessequencecallapisfeign.services.interfaces.IntegrationStepsInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductIntegrationServiceImpl implements IntegrationStepsInterface<ProductResponse> {

    @Autowired
    private ProductAPIClient productAPI;

    @Override
    public ProductResponse execute(IntegrationSteps integrationSteps) {
        log.info("Calling product API.");
        return this.productAPI.createProduct(ProductRequest.builder()
                .nameProduct(integrationSteps.getProductIntegrationRequest().getProductName())
                .idCategory(integrationSteps.getCategoryResponse().getId())
                .build());
    }

}
