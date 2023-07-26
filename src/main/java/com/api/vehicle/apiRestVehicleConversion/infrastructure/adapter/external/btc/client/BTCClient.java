package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.client;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.dto.BTCResponseDTO;
import reactor.core.publisher.Flux;

public interface BTCClient {
    Flux<BTCResponseDTO> getBTCByFilter(String codeCrypto);
}
