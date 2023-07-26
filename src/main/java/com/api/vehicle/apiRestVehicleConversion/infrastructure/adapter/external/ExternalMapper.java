package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external;

import com.api.vehicle.apiRestVehicleConversion.domain.model.external.CarExternalModel;
import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.models.dto.CarResponseDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ExternalMapper {

    ExternalMapper INSTANCE = Mappers.getMapper(ExternalMapper.class);
    @Mappings({
            @Mapping(source ="name",  target= "version")
    })
    CarExternalModel mapToCarModelExternal(CarResponseDTO responseCarModel);
}
