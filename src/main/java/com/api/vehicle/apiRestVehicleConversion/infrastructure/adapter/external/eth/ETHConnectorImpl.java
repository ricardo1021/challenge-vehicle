package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth;

import com.api.vehicle.apiRestVehicleConversion.domain.model.enums.CryptoCurrencyEnum;
import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CryptocurrencyExternalModel;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CryptoCurrencyConnector;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.client.BTCClient;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.client.ETHClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ETHConnectorImpl implements CryptoCurrencyConnector {

    private final ETHClient ethClient;

    private final BTCClient btcClient;
    @Override
    public Mono<CryptocurrencyExternalModel> getCryptoCurrencyPriceByCurrency(CryptoCurrencyEnum cryptoCurrencyEnum, String currency) {
        return ethClient.getEthByFilters(cryptoCurrencyEnum.getName(), currency)
                .map(ethResponse -> CryptocurrencyExternalModel.builder().priceUsd(ethResponse.getEthData().getLastPriceUSD()).code(currency).build())
                .doOnSuccess(live-> log.info("Get live coin watch successful price: {}", live.getPriceUsd()))
                .doOnError(throwable -> log.error("Error consume live coin watch: {}" , throwable.getMessage()));
    }

    public Mono<CryptocurrencyExternalModel> getCryptoCurrencyFallback(CryptoCurrencyEnum cryptocurrency, String currency, Throwable throwable) {
        log.info("Fallback Method getCryptoCurrencyFallback");
        return btcClient.getBTCByFilter(cryptocurrency.getValue())
                .next()
                .map(liveCoinWatchResponse -> {
                    log.info(liveCoinWatchResponse.toString());
                    return CryptocurrencyExternalModel.builder().priceUsd(liveCoinWatchResponse.getPriceUsd()).code(currency).build();
                })
                .doOnSuccess(live-> log.info("Get live coin watch successful {}", live.getPriceUsd()))
                .doOnError(error -> log.error("Error consume live coin watch: {}" , throwable.getMessage()));
    }
}
