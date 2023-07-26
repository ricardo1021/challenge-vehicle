package com.api.vehicle.apiRestVehicleConversion.domain.port.service;

import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelConversionRequest;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelConversionResponse;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelPurchaseRequest;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelPurchaseResponse;
import reactor.core.publisher.Mono;

public interface QuotationService {

    Mono<VehicleApiModelConversionResponse> getResponseVehicleQuote(VehicleApiModelConversionRequest vehicleApiModelConversionRequest);

    Mono<VehicleApiModelPurchaseResponse> getResponseVehicleOrder(VehicleApiModelPurchaseRequest vehicleApiModelPurchaseRequest);

}
