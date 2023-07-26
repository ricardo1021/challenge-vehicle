package com.api.vehicle.apiRestVehicleConversion.domain.port.external;

import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CryptocurrencyExternalModel;
import reactor.core.publisher.Mono;

public interface CryptoCurrencyConnector {

    Mono<CryptocurrencyExternalModel> getCryptoCurrencyPriceByCurrency(CryptoCurrencyEnum cryptoCurrencyEnum, String currency);
}
