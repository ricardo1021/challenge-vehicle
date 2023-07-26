package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models;

import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CarExternalModel;
import com.api.vehicle.apiRestVehicleConversion.domain.port.external.CarModelConnector;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.ExternalMapper;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models.client.CarModelClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarModelConnectorImpl implements CarModelConnector {

    private final CarModelClient carModelClient;

    @Override
    public Flux<CarExternalModel> getCarModelByIdAndModel(String id, String model) {
        return carModelClient.getLiveCoinWatchByFilters(id, model)
                .map(responseCarModel -> ExternalMapper.INSTANCE.mapToCarModelExternal(responseCarModel))
                .doOnComplete(()-> log.info("getLiveCoinWatchByFilters successful"))
                .doOnError(throwable -> log.error("Error getLiveCoinWatchByFilters: {}" , throwable.getMessage()));
    }
}
