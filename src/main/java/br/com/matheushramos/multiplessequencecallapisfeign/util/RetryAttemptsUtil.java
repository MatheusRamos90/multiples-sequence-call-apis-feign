package br.com.matheushramos.multiplessequencecallapisfeign.util;

import br.com.matheushramos.multiplessequencecallapisfeign.config.RetryConfig;
import br.com.matheushramos.multiplessequencecallapisfeign.exception.IntegrationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
public class RetryAttemptsUtil {

    @Autowired
    private RetryConfig retryConfig;

    @SneakyThrows
    public <T> Optional<T> tryExecute(Supplier<T> action) {
        return tryExecute(action, null, this.retryConfig.getAttempts());
    }

    @SneakyThrows
    public <T> Optional<T> tryExecute(Supplier<T> action, Exception lastException, int remainingRetries) {
        if (Objects.equals(remainingRetries, 0)) {
            throw new IntegrationException("Error after to exced retry attempts", lastException, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }

        try {
            return Optional.ofNullable(action.get());
        } catch (Exception e) {
            log.error("Error to execute the action. Remaining attempts: {}. Exception: {}", remainingRetries, e.getMessage());
            Thread.sleep(this.retryConfig.getDelay());
            return tryExecute(action, e, remainingRetries - 1);
        }
    }

}
