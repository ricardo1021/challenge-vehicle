package com.api.vehicle.apiRestVehicleConversion.infrastructure.config;

import com.api.vehicle.apiRestVehicleConversion.application.service.impl.VehicleQuotationServiceImpl;
import com.api.vehicle.apiRestVehicleConversion.domain.port.cache.QuotationCacheRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CarModelConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CryptoCurrencyConnector;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.ModelRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.repository.QuotationRepository;
import com.api.vehicle.apiRestVehicleConversion.domain.port.service.QuotationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanQuoteConfiguration {

    @Bean
    QuotationService quoteBeanService(final QuotationCacheRepository quotationCacheRepository, final CarModelConnector carModelConnector,
                                      final ModelRepository modelRepository, final QuotationRepository quotationRepository,
                                      final CryptoCurrencyConnector cryptoCurrencyConnector){

        return new VehicleQuotationServiceImpl(quotationCacheRepository, carModelConnector, modelRepository, quotationRepository, cryptoCurrencyConnector);

    }
}
