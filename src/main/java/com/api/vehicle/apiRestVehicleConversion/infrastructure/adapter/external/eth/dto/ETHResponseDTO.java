package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ETHResponseDTO {
    private ETHData ethData;
}
