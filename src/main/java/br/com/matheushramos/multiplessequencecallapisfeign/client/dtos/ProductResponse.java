package br.com.matheushramos.multiplessequencecallapisfeign.client.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Integer id;
    private String nameProduct;
    private Integer idCategory;

}
