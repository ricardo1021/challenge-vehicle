package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter;

import com.api.vehicle.apiRestVehicleConversion.domain.model.CarModel;
import com.api.vehicle.apiRestVehicleConversion.domain.model.QuotationModel;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.entity.CatalogEntity;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.entity.QuotationEntity;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface MapperRepository {
    MapperRepository INSTANCE = Mappers.getMapper(MapperRepository.class);
    @Mappings({
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "priceUsd", ignore = true)
    })
    CarModel mapToCarModel(CatalogEntity catalogEntity);

    QuotationEntity mapToQuote(QuotationModel quote);

    QuotationModel mapToQuotePersistenceModel(QuotationEntity quote);
}
