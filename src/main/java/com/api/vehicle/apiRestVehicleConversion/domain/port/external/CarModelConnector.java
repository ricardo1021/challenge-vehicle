package com.api.vehicle.apiRestVehicleConversion.domain.port.external;

import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CarExternalModel;
import reactor.core.publisher.Flux;

public interface CarModelConnector {

    Flux<CarExternalModel> getCarModelByIdAndModel(String id, String model);
}
