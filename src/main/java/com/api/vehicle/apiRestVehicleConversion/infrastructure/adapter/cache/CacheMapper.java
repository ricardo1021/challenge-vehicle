package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache;

import com.api.vehicle.apiRestVehicleConversion.domain.model.cache.QuotationCacheModel;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache.entity.QuoteRedis;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface CacheMapper {

    CacheMapper INSTANCE = Mappers.getMapper(CacheMapper.class);

    QuotationCacheModel mapToQuoteCacheModel(QuoteRedis quoteRedis);

    @InheritInverseConfiguration
    QuoteRedis mapToQuoteRedis(QuotationCacheModel quote);

}
