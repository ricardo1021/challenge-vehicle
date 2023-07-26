package com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.external.btc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BTCResponseDTO {
    @JsonProperty("price_usd")
    private BigDecimal priceUsd;
}
