package com.api.vehicle.apiRestVehicleConversion.domain.port.repository;

import com.api.vehicle.apiRestVehicleConversion.domain.model.CarModel;
import reactor.core.publisher.Mono;

public interface ModelRepository {

    Mono<CarModel> findByName(String name);
}
