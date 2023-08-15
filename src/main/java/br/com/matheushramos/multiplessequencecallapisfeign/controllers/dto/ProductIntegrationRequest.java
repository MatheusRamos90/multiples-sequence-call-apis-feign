package br.com.matheushramos.multiplessequencecallapisfeign.controllers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductIntegrationRequest {

    private String productName;
    private Long amount;

}
