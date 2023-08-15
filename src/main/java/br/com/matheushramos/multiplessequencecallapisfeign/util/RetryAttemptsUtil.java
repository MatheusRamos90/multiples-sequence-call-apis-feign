package br.com.matheushramos.multiplessequencecallapisfeign.util;

import br.com.matheushramos.multiplessequencecallapisfeign.exception.IntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public abstract class RetryAttemptsUtil {

    private RetryAttemptsUtil() {}

    @Value("${retry.attempts}")
    private static int attempts;

    @Value("${retry.message}")
    private static String message;

    public static <T> Optional<T> tryExecute(Supplier<T> action) {
        return tryExecute(action, null, 3);
    }

    public static <T> Optional<T> tryExecute(Supplier<T> action, Exception lastException, int remainingRetries) {
        if (Objects.equals(remainingRetries, 0)) {
            throw new IntegrationException("Error after to exced retry attempts", lastException, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }

        try {
            return Optional.ofNullable(action.get());
        } catch (Exception e) {
            log.error("Error to execute the action. Remaining attempts: {}. Exception: {}", remainingRetries, e.getMessage());
            return tryExecute(action, e, remainingRetries - 1);
        }
    }

}
