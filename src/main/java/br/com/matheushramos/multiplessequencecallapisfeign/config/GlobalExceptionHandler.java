package br.com.matheushramos.multiplessequencecallapisfeign.config;

import br.com.matheushramos.multiplessequencecallapisfeign.dto.ErrorResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.exception.IntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ErrorResponse> handleIntegrationException(IntegrationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
                        .status(e.getStatus())
                        .message(e.getMessage())
                .build());
    }

}
