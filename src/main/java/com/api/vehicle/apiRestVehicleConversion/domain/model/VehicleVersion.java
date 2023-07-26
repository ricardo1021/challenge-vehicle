package com.api.vehicle.apiRestVehicleConversion.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class VehicleVersion {

    private String model;
    private String version;
    private BigDecimal priceUsd;
    private BigDecimal priceCryptocurrency;
    private String cryptocurrency;

}
