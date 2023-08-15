package br.com.matheushramos.multiplessequencecallapisfeign.client.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockResponse {

    private Integer id;
    private Integer idProduct;
    private Integer idCategory;
    private String status;

}
