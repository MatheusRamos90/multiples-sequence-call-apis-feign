package br.com.matheushramos.multiplessequencecallapisfeign.services.interfaces;

import br.com.matheushramos.multiplessequencecallapisfeign.dto.IntegrationSteps;

public interface IntegrationStepsInterface<T> {

    T execute(IntegrationSteps integrationSteps);

}
