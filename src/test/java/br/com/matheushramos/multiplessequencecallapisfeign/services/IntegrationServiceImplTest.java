package br.com.matheushramos.multiplessequencecallapisfeign.services;

import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.CategoryResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.ProductResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.client.dtos.StockResponse;
import br.com.matheushramos.multiplessequencecallapisfeign.config.RetryConfig;
import br.com.matheushramos.multiplessequencecallapisfeign.controllers.dto.ProductIntegrationRequest;
import br.com.matheushramos.multiplessequencecallapisfeign.exception.IntegrationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationServiceImplTest {

    @InjectMocks
    private IntegrationServiceImpl integrationService;

    @Mock
    private CategoryIntegrationServiceImpl categoryIntegrationService;

    @Mock
    private ProductIntegrationServiceImpl productIntegrationService;

    @Mock
    private StockIntegrationServiceImpl stockIntegrationService;

    @Mock
    private RetryConfig retryConfig;

    @Before
    public void setup() {
        when(retryConfig.getAttempts()).thenReturn(3);
        when(retryConfig.getDelay()).thenReturn(1L);
    }

    @Test
    public void testSuccessfulIntegration() {
        ProductIntegrationRequest request = mockProductIntegrationRequest();

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(1)
                .name("Category 01")
                .build();
        when(categoryIntegrationService.execute(any())).thenReturn(categoryResponse);

        ProductResponse productResponse = ProductResponse.builder()
                .id(1)
                .idCategory(1)
                .nameProduct("Product 01")
                .build();
        when(productIntegrationService.execute(any())).thenReturn(productResponse);

        StockResponse stockResponse = StockResponse.builder()
                .id(1)
                .idCategory(1)
                .idProduct(1)
                .status("SUCESS")
                .build();
        when(stockIntegrationService.execute(any())).thenReturn(stockResponse);

        integrationService.executeIntegration(request);

        verify(categoryIntegrationService).execute(any());
        verify(productIntegrationService).execute(any());
        verify(stockIntegrationService).execute(any());
    }

    @Test
    public void testCategoryIntegrationError() {
        ProductIntegrationRequest request = mockProductIntegrationRequest();

        when(categoryIntegrationService.execute(any())).thenThrow(new IntegrationException("Category integration error", new Exception("Erro"), 500));

        try {
            integrationService.executeIntegration(request);
            fail("Exception not thrown");
        } catch (IntegrationException e) {
            verify(categoryIntegrationService, times(3)).execute(any());
            verify(productIntegrationService, never()).execute(any());
            verify(stockIntegrationService, never()).execute(any());
            assertEquals("Error after to exced retry attempts", e.getMessage());
        }
    }

    @Test
    public void testProductIntegrationError() {
        ProductIntegrationRequest request = mockProductIntegrationRequest();

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(1)
                .name("Category 01")
                .build();
        when(categoryIntegrationService.execute(any())).thenReturn(categoryResponse);

        when(productIntegrationService.execute(any())).thenThrow(new IntegrationException("Product integration error", new Exception("Erro"), 500));

        try {
            integrationService.executeIntegration(request);
            fail("Exception not thrown");
        } catch (IntegrationException e) {
            verify(categoryIntegrationService).execute(any());
            verify(productIntegrationService, times(3)).execute(any());
            verify(stockIntegrationService, never()).execute(any());
            assertEquals("Error after to exced retry attempts", e.getMessage());
        }
    }

    @Test
    public void testStockIntegrationError() {
        ProductIntegrationRequest request = mockProductIntegrationRequest();

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(1)
                .name("Category 01")
                .build();
        when(categoryIntegrationService.execute(any())).thenReturn(categoryResponse);

        ProductResponse productResponse = ProductResponse.builder()
                .id(1)
                .idCategory(1)
                .nameProduct("Product 01")
                .build();
        when(productIntegrationService.execute(any())).thenReturn(productResponse);

        when(stockIntegrationService.execute(any())).thenThrow(new IntegrationException("Stock integration error", new Exception("Erro"), 500));

        try {
            integrationService.executeIntegration(request);
            fail("Exception not thrown");
        } catch (IntegrationException e) {
            verify(categoryIntegrationService).execute(any());
            verify(productIntegrationService).execute(any());
            verify(stockIntegrationService, times(3)).execute(any());
            assertEquals("Error after to exced retry attempts", e.getMessage());
        }
    }

    private ProductIntegrationRequest mockProductIntegrationRequest() {
        return ProductIntegrationRequest.builder()
                .productName("Product 01")
                .amount(200L)
                .build();
    }
}

