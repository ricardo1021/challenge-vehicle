package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models.client;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models.dto.CarResponseDTO;
import reactor.core.publisher.Flux;

public interface CarModelClient {
    Flux<CarResponseDTO> getLiveCoinWatchByFilters(String id, String model);
}
