package br.com.matheushramos.multiplessequencecallapisfeign.client.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

    private Integer id;
    private String name;

}
