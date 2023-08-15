package br.com.matheushramos.multiplessequencecallapisfeign.controllers;

import br.com.matheushramos.multiplessequencecallapisfeign.controllers.dto.ProductIntegrationRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.services.IntegrationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductIntegrationController {

    @Autowired
    private IntegrationServiceImpl integrationService;

    @PostMapping
    public ResponseEntity<Object> productIntegration(@RequestBody ProductIntegrationRequest productRequest) {
        log.info("Beginning integration. RequestBody: {}", productRequest);
        this.integrationService.executeIntegration(productRequest);
        log.info("Ending integration successfully.");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
