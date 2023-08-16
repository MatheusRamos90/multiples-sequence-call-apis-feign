package br.com.matheushramos.multiplessequencecallapisfeign.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RetryConfig {

    @Value("${retry.attempts}")
    private int attempts;

    @Value("${retry.delay}")
    private Long delay;

}
