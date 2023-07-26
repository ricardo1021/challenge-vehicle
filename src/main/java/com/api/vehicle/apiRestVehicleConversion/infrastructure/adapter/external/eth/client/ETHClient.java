package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.client;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.dto.ETHResponseDTO;
import reactor.core.publisher.Mono;

public interface ETHClient {

    Mono<ETHResponseDTO> getEthByFilters(String cryptocurrency, String currency);
}
