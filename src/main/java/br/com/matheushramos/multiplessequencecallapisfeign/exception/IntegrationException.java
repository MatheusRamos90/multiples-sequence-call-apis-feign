package br.com.matheushramos.multiplessequencecallapisfeign.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class IntegrationException extends RuntimeException {

    private Integer status;

    public IntegrationException(String message, Throwable cause, Integer status) {
        super(message, cause);
        this.status = status;
    }

}
