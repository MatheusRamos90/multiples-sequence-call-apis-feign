package br.com.matheushramos.multiplessequencecallapisfeign.client.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private String name;

}
