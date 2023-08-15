package br.com.matheushramos.multiplessequencecallapisfeign.client.dtos;

import br.com.matheushramos.multiplessequencecallapisfeign.enums.EnumStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockRequest {

    private Integer idProduct;
    private Integer idCategory;
    private Long amountProduct;
    private EnumStatus status;

}
