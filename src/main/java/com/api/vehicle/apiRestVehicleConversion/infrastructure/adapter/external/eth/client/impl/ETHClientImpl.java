package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.client.impl;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.client.ETHClient;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.dto.ETHResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ETHClientImpl implements ETHClient {

    @Value("${spring.eth.coin.uri}")
    private String uri;
    @Override
    public Mono<ETHResponseDTO> getEthByFilters(String cryptocurrency, String currency) {
        WebClient client = WebClient.create();
        String url = String.format(uri, cryptocurrency, currency);
        return client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ETHResponseDTO.class)
                //.transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                .map(ethResponseDTO -> {
                    if(ethResponseDTO.getEthData() == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                    return ethResponseDTO;
                }).retry(3)
                //.onErrorMap(throwable -> new RuntimeException());
                .doOnError(throwable -> log.error("Error LiveCoinWatchClient: {}" , throwable.getMessage()));
    }
}
