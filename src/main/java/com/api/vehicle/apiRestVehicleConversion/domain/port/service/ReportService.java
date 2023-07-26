package com.api.vehicle.apiRestVehicleConversion.domain.port.service;

import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelReportRequest;
import com.api.vehicle.apiRestVehicleConversion.domain.model.api.VehicleApiModelReportResponse;
import reactor.core.publisher.Flux;

public interface ReportService {

    Flux<VehicleApiModelReportResponse> getResponseGenerateReportApiModelFlux(VehicleApiModelReportRequest vehicleApiModelReportRequest);

}
