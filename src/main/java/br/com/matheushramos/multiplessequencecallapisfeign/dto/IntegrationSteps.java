package br.com.matheushramos.multiplessequencecallapisfeign.dto;

import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.controllers.dto.ProductIntegrationRequest;
import lombok.Data;

@Data
public class IntegrationSteps {

    private ProductIntegrationRequest productIntegrationRequest;
    private CategoryResponse categoryResponse;
    private ProductResponse productResponse;
    private StockResponse stockResponse;

}
