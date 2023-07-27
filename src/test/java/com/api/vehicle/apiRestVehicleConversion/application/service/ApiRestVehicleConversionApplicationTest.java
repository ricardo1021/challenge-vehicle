package com.api.vehicle.apiRestVehicleConversion.application.service;

import com.api.vehicle.apiRestVehicleConversion.application.service.impl.VehicleQuotationServiceImpl;
import com.api.vehicle.apiRestVehicleConversion.domain.port.cache.QuotationCacheRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CarModelConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CryptoCurrencyConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.ModelRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.QuotationRepository;
import com.api.vehicle.apiRestVehicleConversion.util.Constants;
import com.api.vehicle.apiRestVehicleConversion.util.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@ContextConfiguration(classes = {ApiRestVehicleConversionApplicationTest.class})
class ApiRestVehicleConversionApplicationTest {

    @Mock
    private QuotationCacheRepository quotationCacheRepository;

    @Mock
    private CarModelConnector carModelConnector;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private QuotationRepository quotationRepository;

    @Mock
    private CryptoCurrencyConnector cryptoCurrencyConnector;

    @InjectMocks
    private VehicleQuotationServiceImpl vehicleQuotationService;

    @Test
    void getResponseVehicleQuote() {
        Mockito.when(modelRepository.findByName(Mockito.anyString()))
                .thenReturn(Mono.just(MockData.getCarPersistenceModel()));
        Mockito.when(cryptoCurrencyConnector.getCryptoCurrencyPriceByCurrency(Mockito.any(), Mockito.anyString()))
                .thenReturn(Mono.just(MockData.getCryptocurrencyExternalModel()));
        Mockito.when(carModelConnector.getCarModelByIdAndModel(Mockito.any(), Mockito.anyString()))
                .thenReturn(Flux.just(MockData.getCarModelExternalModel1(), MockData.getCarModelExternalModel2()));
        String uuidRevision = UUID.randomUUID().toString();
        Mockito.when(quotationCacheRepository.saveQuote(Mockito.any())).thenReturn(Mono.just(uuidRevision));

        StepVerifier.create(vehicleQuotationService.getResponseVehicleQuote(MockData.getRequestGenerateQuoteApiModel()))
                .consumeNextWith(responseGenerateQuoteApiModel -> Assertions.assertEquals(responseGenerateQuoteApiModel.getConvertionId(), uuidRevision))
                .verifyComplete()
        ;
        Mockito.verify(modelRepository, Mockito.times(1)).findByName(Mockito.anyString());
        Mockito.verify(carModelConnector, Mockito.times(1)).getCarModelByIdAndModel(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(quotationCacheRepository, Mockito.times(1)).saveQuote(Mockito.any());


    }

    @Test
    void getResponseVehicleOrder() {
        Mockito.when(quotationCacheRepository.findQuote(Mockito.any())).thenReturn(Mono.just(MockData.getQuoteCacheModel()));
        Mockito.when(quotationRepository.saveQuotePersistenceModel(Mockito.any())).thenReturn(Mono.just(MockData.getQuotePersistenceModel()));

        StepVerifier.create(vehicleQuotationService.getResponseVehicleOrder(MockData.getRequestSaveQuoteApiModel()))
                .consumeNextWith(responseSaveQuoteApiModel -> {
                    Assertions.assertNotNull(responseSaveQuoteApiModel);
                })
                .verifyComplete()
        ;
        Mockito.verify(quotationCacheRepository, Mockito.times(1)).findQuote(Mockito.any());
        Mockito.verify(quotationRepository, Mockito.times(1)).saveQuotePersistenceModel(Mockito.any());
    }

    @Test
    void getResponseGenerateReportApiModelFlux() {

        Mockito.when(quotationRepository.findQuotePersistenceModelBy(Mockito.anyString(), Mockito.anyString(), Mockito.any()))
                .thenReturn(Flux.just(
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_1, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_1),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_1, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_1),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_2, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_2),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_1, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_1),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_1, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_1),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_1, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_1),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_2, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_2),
                        MockData.getQuotePersistenceModel(Constants.ACCENT_MODEL, Constants.VERSION_ACCENT_2, Constants.PRICE_CRYPTOCURRENCY, Constants.PRICE_VERSION_ACCENT_2)
                ));
        StepVerifier.create(vehicleQuotationService.getResponseGenerateReportApiModelFlux(MockData.getRequestGenerateReportApiModel()))
                .consumeNextWith(responseGenerateReportApiModel -> {
                    switch (responseGenerateReportApiModel.getVersion()) {
                        case Constants.VERSION_ACCENT_1 -> {
                            Assertions.assertEquals(responseGenerateReportApiModel.getCryptocurrencyAmount(), Constants.PRICE_CRYPTOCURRENCY.multiply(new BigDecimal(5)));
                            Assertions.assertEquals(responseGenerateReportApiModel.getUsdAmount(), Constants.PRICE_VERSION_ACCENT_1.multiply(new BigDecimal(5)));
                        }
                        case Constants.VERSION_ACCENT_2 -> {
                            Assertions.assertEquals(responseGenerateReportApiModel.getCryptocurrencyAmount(), Constants.PRICE_CRYPTOCURRENCY.multiply(new BigDecimal(3)));
                            Assertions.assertEquals(responseGenerateReportApiModel.getUsdAmount(), Constants.PRICE_VERSION_ACCENT_2.multiply(new BigDecimal(3)));
                        }
                        default -> Assertions.assertFalse(false);
                    }

                })
                .consumeNextWith(responseGenerateReportApiModel -> {
                    switch (responseGenerateReportApiModel.getVersion()) {
                        case Constants.VERSION_ACCENT_1 -> {
                            Assertions.assertEquals(responseGenerateReportApiModel.getCryptocurrencyAmount(), Constants.PRICE_CRYPTOCURRENCY.multiply(new BigDecimal(5)));
                            Assertions.assertEquals(responseGenerateReportApiModel.getUsdAmount(), Constants.PRICE_VERSION_ACCENT_1.multiply(new BigDecimal(5)));
                        }
                        case Constants.VERSION_ACCENT_2 -> {
                            Assertions.assertEquals(responseGenerateReportApiModel.getCryptocurrencyAmount(), Constants.PRICE_CRYPTOCURRENCY.multiply(new BigDecimal(3)));
                            Assertions.assertEquals(responseGenerateReportApiModel.getUsdAmount(), Constants.PRICE_VERSION_ACCENT_2.multiply(new BigDecimal(3)));
                        }
                        default -> Assertions.assertFalse(false);
                    }

                })
                .verifyComplete()
                ;

        Mockito.verify(quotationRepository, Mockito.times(1)).findQuotePersistenceModelBy(Mockito.anyString(), Mockito.anyString(), Mockito.any());
    }
}