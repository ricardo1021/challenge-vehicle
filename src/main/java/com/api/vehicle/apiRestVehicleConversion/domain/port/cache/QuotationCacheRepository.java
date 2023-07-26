package com.api.vehicle.apiRestVehicleConversion.domain.port.cache;

import com.api.vehicle.apiRestVehicleConversion.domain.model.cache.QuotationCacheModel;
import reactor.core.publisher.Mono;

public interface QuotationCacheRepository {

    Mono<String> saveQuote(QuotationCacheModel quotationCacheModel);

    Mono<QuotationCacheModel> findQuote(String convertionId);
}