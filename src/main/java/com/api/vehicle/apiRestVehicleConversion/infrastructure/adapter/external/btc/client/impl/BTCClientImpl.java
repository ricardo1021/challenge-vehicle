package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.client.impl;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.client.BTCClient;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.dto.BTCResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class BTCClientImpl implements BTCClient {

    @Value("${spring.btc.coin.uri}")
    private String uri;

    @Override
    public Flux<BTCResponseDTO> getBTCByFilter(String codeCrypto) {
        WebClient client = WebClient.create();
        return client.get()
                .uri(uri.concat("?id=").concat(codeCrypto))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BTCResponseDTO.class)
                .map(responseCoinLore -> {
                    log.info(responseCoinLore.toString());
                    return responseCoinLore;
                });
    }
}
