package com.api.vehicle.apiRestVehicleConversion.domain.port.repository;

import com.api.vehicle.apiRestVehicleConversion.domain.model.QuotationModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface QuotationRepository {

    Mono<QuotationModel> saveQuotePersistenceModel(QuotationModel quotationModel);

    Flux<QuotationModel> findQuotePersistenceModelBy(String model, String cryptocurrency, LocalDate date);

}
