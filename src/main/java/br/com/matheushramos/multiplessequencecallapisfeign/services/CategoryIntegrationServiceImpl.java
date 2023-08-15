package br.com.matheushramos.multiplessequencecallapisfeign.services;

import br.com.matheushramos.multiplessequencecallapisfeign.client.apis.CategoryAPIClient;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.dto.IntegrationSteps;
import br.com.matheushramos.multiplessequencecallapisfeign.services.interfaces.IntegrationStepsInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryIntegrationServiceImpl implements IntegrationStepsInterface<CategoryResponse> {

    @Autowired
    private CategoryAPIClient categoryAPI;

    @Override
    public CategoryResponse execute(IntegrationSteps integrationSteps) {
        log.info("Calling category API.");
        return this.categoryAPI.createCategory(CategoryRequest.builder()
                .name(integrationSteps.getProductIntegrationRequest().getProductName())
                .build());
    }

}
