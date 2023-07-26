package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.eth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ETHData {
    private BigDecimal lastPriceUSD;
}
